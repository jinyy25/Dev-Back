package kr.vtw.kms.search.controller;

import kr.vtw.kms.search.service.FileOptionService;
import kr.vtw.kms.search.service.TypeCdService;
import kr.vtw.kms.search.dto.OptionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchOptionController {

    @Autowired
    private FileOptionService fileOptionService;

    @Autowired
    private TypeCdService searchTypeCdService;

    @GetMapping("/orgnm")
    public List<String> getOrgnm() {
        return fileOptionService.getOrgnm();
    }

    @GetMapping("/stype")
    public List<String> getStype() {
        return searchTypeCdService.getStype();
    }

    @PostMapping("/projectnm")
    public List<String> getProjectnm(@RequestBody OptionRequestDto requestVo) {
        return fileOptionService.getProjectnm(requestVo);
    }

    @GetMapping("/year")
    public List<String> getYear() {
        return fileOptionService.getYear();
    }

}
