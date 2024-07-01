package kr.vtw.kms.faq.service;

import kr.vtw.kms.faq.dto.FaqDto;
import kr.vtw.kms.faq.mapper.FaqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RaqService")
public class FaqService {

    @Autowired
    public FaqMapper mapper;

    public int faqInsert(FaqDto raqVo){
        return mapper.faqInsert(raqVo);
    }
}
