package kr.vtw.kms.ask.service;

import kr.vtw.kms.ask.dto.AskDto;
import kr.vtw.kms.ask.dto.AskRequestDto;
import kr.vtw.kms.ask.mapper.AskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AskService")
public class AskService {

    @Autowired
    public AskMapper mapper;

    public int askInsert(AskDto reqVo){
        return mapper.askInsert(reqVo);
    }

    public List<AskDto> askSearch(AskRequestDto reqVo){
         return mapper.askSearch(reqVo);
     }
}
