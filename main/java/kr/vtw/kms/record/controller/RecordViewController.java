package kr.vtw.kms.record.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/videos")
public class RecordViewController {

    @Value("${reference.path}")
    private String videoPath;

       @GetMapping("/{filename}")
       public ResponseEntity<Resource> downloadVideo(@PathVariable String filename) throws MalformedURLException {
           Path filePath = Paths.get(videoPath, filename);
           Resource resource = new UrlResource(filePath.toUri());

           return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                   .body(resource);
       }
}
