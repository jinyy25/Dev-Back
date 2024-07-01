package kr.vtw.kms.common.service;

import kr.vtw.kms.common.dto.DownloadHistoryDto;
import kr.vtw.kms.common.dto.DownloadRequestDto;
import kr.vtw.kms.common.mapper.FileMapper;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service("FileDownloadService")
public class FileDownloadService {


    @Autowired
    public FileMapper mapper;


    @Value("${slide.path}")
    private String slidePath;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${reference.path}")
    private String referencePath;

    @Value("${pdf.path}")
    private String ppttopdfPath;

    public Resource downloadFile(DownloadRequestDto requestBody, HttpServletRequest request) throws IOException {

        int downloadType = requestBody.getType();
        Path filePath = null;

        // 파일 경로 설정
        // 1: PDF파일
        // 2: 슬라이드파일 (PPTX)
        // 3: HWP파일
        // 4: 참고파일
        switch (downloadType) {
            case 1: filePath = Paths.get(uploadPath, requestBody.getFileName());
                break;
            case 2: filePath = Paths.get(slidePath, requestBody.getFileName());
                break;
            case 3: filePath = Paths.get(uploadPath, requestBody.getFileName());
                break;
            case 4: filePath = Paths.get(referencePath, requestBody.getFileName());
                break;
            case 5: filePath = Paths.get(ppttopdfPath, requestBody.getFileName());
                break;
            default:
                break;
        }
        if (filePath == null) throw new FileNotFoundException("File path not found");

        // 파일을 Resource로 변환
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists() || !resource.isReadable()) throw new FileNotFoundException("File not found or cannot be read");

        //전체 다운로드일경우 이력남길 예정
        if(requestBody.getUserId() != null){
            DownloadHistoryDto downloadHistoryDto = new DownloadHistoryDto();
            downloadHistoryDto.setUserId(requestBody.getUserId());
            downloadHistoryDto.setFileName(requestBody.getFileName());
            downloadHistoryDto.setFilePath(filePath.toString());
            downloadHistoryDto.setIpAddress(getClientIp(request));
            downloadHistoryDto.setFileExt(requestBody.getFileExt());
            downloadHistoryDto.setFileCode(requestBody.getFileCode());
            int result = insertHistory(downloadHistoryDto);
        }


        return resource;
    }

    public int insertHistory(DownloadHistoryDto downloadHistoryDto){

        return mapper.insertHistory(downloadHistoryDto);
    }


    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


    //다건 다운로드시 PPT 병합 로직
    public String mergedUpload(DownloadRequestDto requestBody){

           Path filePath = Paths.get(slidePath, requestBody.getFileName());
           List<String> pptxArray = requestBody.getPptxArray();
           List<String> pptPath = new ArrayList<>();

           //1. path 뒤에 가져온 배열 붙여넣기
           for(String dataPath : pptxArray){
               String finalPath=filePath+"/"+dataPath;
               pptPath.add(finalPath);
           }

      		//2. 첫번째 파일을 기준으로 파일생성 및 프레젠테이션 생성
           try {
               ZipSecureFile.setMinInflateRatio(0);
               XMLSlideShow defaultSlide = new XMLSlideShow(new FileInputStream(pptPath.get(0)));



               //3. 첫번째 ppt 는 default 값으로 지정 (master를 가져와야 함으로)
               for (int i = 1; i < pptPath.size(); i++) {
                       XMLSlideShow add = new XMLSlideShow( new FileInputStream(pptPath.get(i)));

                       //4. 첫번째 ppt에 슬라이드 가져오되 서식 유지하도록 설정
                       for (XSLFSlide slide : add.getSlides()) {
                           try {
                           XSLFSlide clonedSlide = defaultSlide.createSlide(slide.getSlideLayout());
                           clonedSlide.importContent(slide);
                            }catch(Exception e ){
                    					 e.printStackTrace();
                           }
                       }
               }

               String strReqEndDt = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
               String outP = slidePath +strReqEndDt+ "_tmp.pptx";

               //5. 해당위치에 저장
                FileOutputStream out1 = new FileOutputStream(outP);
                defaultSlide.write(out1);
                out1.close();
                System.out.println("프레젠테이션 병합이 완료되었습니다.");
                return outP;
           }catch (IOException e){
               e.printStackTrace();
           }


           return null;
      	}



}
