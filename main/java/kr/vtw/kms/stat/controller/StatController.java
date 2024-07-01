package kr.vtw.kms.stat.controller;


import kr.vtw.kms.stat.service.StatService;
import kr.vtw.kms.stat.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stat")
public class StatController {

    @Autowired
    private StatService statService;


    @GetMapping("/statdocs")
    public List<StatDocsDto> statdocs(){
        return statService.statdocs();
    }

    @PostMapping("/statperson")
    public List<StatPersonDto> statperson(@RequestBody StatRequestDto requestVo){ return statService.statperson(requestVo); }

    @PostMapping("/statfile")
    public List<StatFileDto> statfile(@RequestBody StatRequestDto requestVo){
        return statService.statfile(requestVo);
    }

    @PostMapping("/statuser")
    public List<StatPersonDto> statuser(@RequestBody StatRequestDto requestVo){ return statService.statuser(requestVo); }

    @PostMapping("/statweek")
    public List<StatWeekDto> statweek(@RequestBody StatRequestDto requestVo){
        return statService.statweek(requestVo);
    }

    @PostMapping("/stattime")
    public List<StatTimeDto> stattime(@RequestBody StatRequestDto requestVo){
        return statService.stattime(requestVo);
    }

    @PostMapping("/statdownload")
    public List<StatDownloadDto> statdownload(@RequestBody StatRequestDto requestVo){ return statService.statdownload(requestVo); }

    @PostMapping("/statmileage")
    public List<StatMileageDto> statmileage(@RequestBody StatRequestDto requestVo){ return statService.statmileage(requestVo); }
}
