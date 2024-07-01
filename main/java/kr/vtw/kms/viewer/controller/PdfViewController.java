package kr.vtw.kms.viewer.controller;

import kr.vtw.kms.viewer.dto.ViewerRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/pdf")
public class PdfViewController {

    @Value("${pdf.path}")
    private String pptxPath;

    @Value("${upload.path}")
    private String pdfPath;


    @PostMapping("/pdfview")
    public ResponseEntity<Resource> getPdfFile(@RequestBody ViewerRequestDto requestVo) throws MalformedURLException {
        String fileExt=requestVo.getFileExt();
        String copyPath;

        if(fileExt.equals("pdf")){
            copyPath= pdfPath;
        }else{
            copyPath= pptxPath;
        }

        Path filePath = Paths.get(copyPath, requestVo.getTmpName());
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
