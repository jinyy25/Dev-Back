package kr.vtw.kms.faq.controller;


import kr.vtw.kms.faq.dto.FaqDto;
import kr.vtw.kms.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class FaqController {

    private final FaqService faqService;

    @PostMapping("/auth/faq")
       public int faqInsert(@RequestBody FaqDto raqVo) {
        return faqService.faqInsert(raqVo);
    }


}
