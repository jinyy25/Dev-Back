package kr.vtw.kms.notice.mapper;


import kr.vtw.kms.notice.dto.NoticeDto;
import kr.vtw.kms.notice.dto.NoticeRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDto> noticeSeaerch (NoticeRequest reqVo);

    int noticeInsert (NoticeDto reqVo);

    NoticeDto noticeView (NoticeDto reqVo);

    int noticeDelete (NoticeDto reqVo);

    int noticeUpdate (NoticeDto reqVo);
}
