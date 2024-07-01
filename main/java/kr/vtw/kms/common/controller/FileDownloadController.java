package kr.vtw.kms.common.controller;


import kr.vtw.kms.common.dto.DownloadRequestDto;
import kr.vtw.kms.common.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URL;


@Controller
public class FileDownloadController {


    @Autowired
    private FileDownloadService fileService;

    @PostMapping("/file/download")
    public ResponseEntity<Resource> downloadFile(@RequestBody DownloadRequestDto requestBody, HttpServletRequest request) throws IOException {
        Resource resource = fileService.downloadFile(requestBody, request);

        // 다운로드 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", resource.getFilename());

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @PostMapping("/file/downloadMulti")
    public ResponseEntity<Resource> downloadMultiFile(@RequestBody DownloadRequestDto requestBody) throws IOException {
        String outP = fileService.mergedUpload(requestBody);
        URL url = new File(outP).toURI().toURL();
        Resource resource = new UrlResource(url);
        return ResponseEntity.ok().body(resource);
    }

    @PostMapping("/file/delete")
    public ResponseEntity<String> deleteFile(@RequestBody DownloadRequestDto requestBody) throws IOException {
   	    File file = new File(requestBody.getFileName());

        if (file.delete()) {
            return ResponseEntity.ok().body("파일이 성공적으로 제거되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 제거 중 오류가 발생했습니다.");
        }


    }





}




