package kr.vtw.kms.common.service;

import kr.vtw.kms.common.dto.UploadDto;
import kr.vtw.kms.common.mapper.FileMapper;
import kr.vtw.kms.reference.dto.MileageDto;
import kr.vtw.kms.reference.mapper.ReferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service("FileUploadService")
public class FileUploadService {

    @Autowired
    public FileMapper mapper;

    @Autowired
    private ReferenceMapper referenceMapper;

    public int fileInsert(UploadDto reqVo){
        int fileUpload = mapper.fileInsert(reqVo);
        MileageDto mileageDto = new MileageDto();
        if(fileUpload ==1){
            mileageDto.setUserId(reqVo.getInid());
            mileageDto.setMileage(1);
            mileageDto.setMileageCategory("upload");
        }

        return referenceMapper.insertMileage(mileageDto);
    }


    //파일명 변경
    public String generateCurrentTimestamp() {
      return new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
    }

    //파일명 변경
    public String generateSaveFileName(String fileName, String timestamp) {
        return timestamp + fileName.substring(fileName.lastIndexOf("."));
    }

    //확장자 추출
    public String extractFileExtension(String fileName) {
          int dotIndex = fileName.lastIndexOf(".");
          return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    //결과 response
    public ResponseEntity<String> uploadFile(MultipartFile file, String saveFileName, String uploadPath) {
        try {
            String filePath = uploadPath + File.separator + saveFileName;
            file.transferTo(new File(filePath));
            return ResponseEntity.ok().body("Success");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }


}
