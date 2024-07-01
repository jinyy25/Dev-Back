package kr.vtw.kms.ask.mapper;


import kr.vtw.kms.ask.dto.AskDto;
import kr.vtw.kms.ask.dto.AskRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AskMapper {

    int askInsert (AskDto reqVo);

    List<AskDto> askSearch (AskRequestDto reqVo);
}
