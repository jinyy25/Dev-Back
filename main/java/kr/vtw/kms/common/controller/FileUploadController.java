package kr.vtw.kms.common.controller;

import kr.vtw.kms.common.dto.UploadDto;
import kr.vtw.kms.common.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/api")
public class FileUploadController {

    @Value("${reference.path}")
    private String uploadPath;

    @Autowired
    private FileUploadService fileService;


    @PostMapping("/upload")
    public ResponseEntity<String> recordUpload (
              @RequestParam("refile") MultipartFile refile,
              @RequestParam(name = "thumbnail", required = false) MultipartFile thumbnail,
              @RequestParam("title") String title,
              @RequestParam("desc") String desc,
              @RequestParam("typecode") int typecode,
              @RequestParam("inid") String inid
    ) {

        //0. 저장 변수 세팅
        String timestamp = fileService.generateCurrentTimestamp();
        UploadDto uploadDto = new UploadDto();
        String fileExt = null;
        String saveFile = null;

        //1. 파일저장
        //썸네일 저장
        if(typecode ==2){
            String saveThumbnail = fileService.generateSaveFileName(thumbnail.getOriginalFilename(), timestamp);
            ResponseEntity<String> thumbnailUploadResult = fileService.uploadFile(thumbnail, saveThumbnail, uploadPath);
            if (thumbnailUploadResult.getStatusCode() != HttpStatus.OK) {
                return thumbnailUploadResult;           
            }
            uploadDto.setSaveThumbname(saveThumbnail);
            uploadDto.setThumbnailName(thumbnail.getOriginalFilename());
        }


        //파일 저장 (동영상, 참고파일)
        saveFile = fileService.generateSaveFileName(refile.getOriginalFilename(), timestamp);
        ResponseEntity<String> uploadResult = fileService.uploadFile(refile, saveFile, uploadPath);
        if (uploadResult.getStatusCode() != HttpStatus.OK) {
            return uploadResult;
        }

        fileExt = fileService.extractFileExtension(refile.getOriginalFilename());


        //2. DB 내용 저장
        //값 세팅
        uploadDto.setFilePath(uploadPath);
        uploadDto.setFileName(refile.getOriginalFilename());
        uploadDto.setFileExt(fileExt);
        uploadDto.setFileSize(refile.getSize());
        uploadDto.setSaveName(saveFile);
        uploadDto.setTitleFile(title);
        uploadDto.setDescFile(desc);
        uploadDto.setInid(inid);

        // 동영상자료, 참고자료 분기처리
        if(typecode ==2){
            uploadDto.setTypeCode(typecode);
            uploadDto.setTypeFile("동영상자료");
        } else if(typecode ==1){
            uploadDto.setTypeCode(typecode);
            uploadDto.setTypeFile("참고자료");
        }
        
        //DB 내용 저장
        int result = fileService.fileInsert(uploadDto);
        if(result==1){
            return ResponseEntity.ok().body("Success");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }

      }



}
