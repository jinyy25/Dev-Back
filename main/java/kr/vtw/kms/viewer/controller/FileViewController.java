package kr.vtw.kms.viewer.controller;


import kr.vtw.kms.viewer.dto.ViewerRequestDto;
import kr.vtw.kms.viewer.dto.ViewerResponseDto;
import kr.vtw.kms.viewer.service.FileInfoService;
import kr.vtw.kms.viewer.service.HwpFileService;
import kr.vtw.kms.viewer.service.PdfSlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;


@RestController
@RequestMapping("/api/view")
public class FileViewController {

    @Value("${hwp.path}")
    private String hwpview;

    @Autowired
    private PdfSlideService pdfSlideService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private HwpFileService hwpFileService;



    @PostMapping("/filedetail")
    public ViewerResponseDto getFileDetail(@RequestBody ViewerRequestDto requestVo) {
        return fileInfoService.getFileDetail(requestVo);
     }

    @PostMapping("/pdfslice")
    public String[] getPdfSlice(@RequestBody ViewerRequestDto requestVo) {
        String[] list = pdfSlideService.pdfViewer(requestVo.getTmpName());
        return list;
    }


    @GetMapping("/{srcdoc}/index.xhtml")
    public ResponseEntity<String> getIndexHtml(@PathVariable String srcdoc) throws IOException { ;

        String filePath = hwpview + srcdoc + "/index.xhtml";
        String cssPath = hwpview + srcdoc + "/styles.css";
        String bindataPath = hwpview + srcdoc + "/bindata";

        try {
            // index.xhtml 파일 내용 읽기
            Path indexPathObj = Paths.get(filePath);
            if (!Files.exists(indexPathObj)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("인덱스 파일을 찾을 수 없습니다");
            }
            String indexContent = new String(Files.readAllBytes(indexPathObj), StandardCharsets.UTF_8);

            // CSS 파일 내용 읽기
            Path cssPathObj = Paths.get(cssPath);
            String cssContent = "";
            if (Files.exists(cssPathObj)) {
                cssContent = new String(Files.readAllBytes(cssPathObj), StandardCharsets.UTF_8);
            }

            // Replace CSS link
            String cssLink = "<style>" + cssContent + "</style>";
            indexContent = indexContent.replace("</head>", cssLink + "\n</head>");

            // Replace image sources
            File bindataFolder = new File(bindataPath);
            if (bindataFolder.exists() && bindataFolder.isDirectory()) {
                for (File imageFile : Objects.requireNonNull(bindataFolder.listFiles())) {
                    String imageName = imageFile.getName();
                    String imageSource = "data:image/" + hwpFileService.getImageExtension(imageName) + ";base64," +
                            Base64.getEncoder().encodeToString(Files.readAllBytes(imageFile.toPath()));
                    String replaceSource = "bindata/"+imageName;
                    indexContent = indexContent.replace(replaceSource, imageSource);

                }
            }

            return ResponseEntity.ok(indexContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 오류");
        }
    }

}
