package kr.vtw.kms.stat.service;

import kr.vtw.kms.stat.mapper.StatMapper;
import kr.vtw.kms.stat.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StatService")
public class StatService {

    @Autowired
    public StatMapper mapper;

    public List<StatDocsDto> statdocs(){
        return mapper.statdocs();
    }

    public List<StatPersonDto> statperson(StatRequestDto requestVo){
        return mapper.statperson(requestVo);
    }

    public List<StatFileDto> statfile(StatRequestDto requestVo){
        return mapper.statfile(requestVo);
    }

    public List<StatPersonDto> statuser(StatRequestDto requestVo){
        return mapper.statuser(requestVo);
    }

    public List<StatWeekDto> statweek(StatRequestDto requestVo){
        return mapper.statweek(requestVo);
    }

    public List<StatTimeDto> stattime(StatRequestDto requestVo){
        return mapper.stattime(requestVo);
    }

    public List<StatDownloadDto> statdownload(StatRequestDto requestVo){
        return mapper.statdownload(requestVo);
    }
    public List<StatMileageDto> statmileage(StatRequestDto requestVo){
        return mapper.statmileage(requestVo);
    }

}
