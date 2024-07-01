package kr.vtw.kms.record.mapper;


import kr.vtw.kms.record.dto.RecordDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    List<RecordDto> recordView (RecordDto reqVo);
    RecordDto recordDetail (RecordDto reqVo);
    int recordDelete (RecordDto reqVo);
    int recordUpdate (RecordDto reqVo);
}
