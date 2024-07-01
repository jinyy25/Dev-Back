package kr.vtw.kms.stat.mapper;

import kr.vtw.kms.stat.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatMapper {
    List<StatDocsDto> statdocs();

    List<StatPersonDto> statperson(StatRequestDto requestVo);

    List<StatFileDto> statfile(StatRequestDto requestVo);

    List<StatPersonDto> statuser(StatRequestDto requestVo);

    List<StatWeekDto> statweek(StatRequestDto requestVo);

    List<StatTimeDto> stattime(StatRequestDto requestVo);

    List<StatDownloadDto> statdownload(StatRequestDto requestVo);

    List<StatMileageDto> statmileage(StatRequestDto requestVo);
}
