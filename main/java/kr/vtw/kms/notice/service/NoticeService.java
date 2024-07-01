package kr.vtw.kms.notice.service;

import kr.vtw.kms.notice.dto.NoticeDto;
import kr.vtw.kms.notice.dto.NoticeRequest;
import kr.vtw.kms.notice.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoticeService")
public class NoticeService {

    @Autowired
    public NoticeMapper mapper;

    public List<NoticeDto> noticeSeaerch(NoticeRequest reqVo){
        return mapper.noticeSeaerch(reqVo);
    }

    public int noticeInsert(NoticeDto reqVo){
        return mapper.noticeInsert(reqVo);
    }

    public NoticeDto noticeView(NoticeDto reqVo){
        return mapper.noticeView(reqVo);
    }

    public int noticeDelete(NoticeDto reqVo){
        return mapper.noticeDelete(reqVo);
    }

    public int noticeUpdate(NoticeDto reqVo){
        return mapper.noticeUpdate(reqVo);
    }


}
