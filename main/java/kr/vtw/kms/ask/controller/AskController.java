package kr.vtw.kms.ask.controller;


import kr.vtw.kms.ask.dto.AskDto;
import kr.vtw.kms.ask.dto.AskRequestDto;
import kr.vtw.kms.ask.service.AskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AskController {

    private final AskService askService;

    @PostMapping("/auth/ask/insert")
    public int askInsert(@RequestBody AskDto reqVo) {
        return askService.askInsert(reqVo);
    }


    @PostMapping("/auth/ask/search")
    public List<AskDto> askSearch(@RequestBody AskRequestDto reqVo) {
        return askService.askSearch(reqVo);
    }
}
