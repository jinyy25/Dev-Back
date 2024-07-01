package kr.vtw.kms.common.controller;

import kr.vtw.kms.common.service.FileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parseFile")
public class FileParserController {


    @Autowired
    private FileParserService fileParserService;


    @GetMapping("/parserpdf")
    public String parserPdf(@RequestParam int filecd) {
        int result = fileParserService.getPdfSource(filecd);
        if (result == 1) {
            return "Received filecd: " + filecd;
        } else {
            return "Failed: " + filecd;
        }
    }


    @GetMapping("/parserhwp")
    public String parserHwp(@RequestParam int filecd) {
        int result = fileParserService.getHwpSource(filecd);
        if (result == 1) {
            return "Received filecd: " + filecd;
        } else {
            return "Failed: " + filecd;
        }
    }

}
