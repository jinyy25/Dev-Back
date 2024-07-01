package kr.vtw.kms.notice.controller;


import kr.vtw.kms.notice.dto.NoticeDto;
import kr.vtw.kms.notice.dto.NoticeRequest;
import kr.vtw.kms.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @PostMapping("/search")
    public List<NoticeDto> noticeSeaerch(@RequestBody NoticeRequest reqVo) {
        return noticeService.noticeSeaerch(reqVo);
    }

    @PostMapping("/insert")
    public int noticeInsert(@RequestBody NoticeDto reqVo) {
        return noticeService.noticeInsert(reqVo);
    }

    @PostMapping("/view")
    public NoticeDto noticeView(@RequestBody NoticeDto reqVo) {
        return noticeService.noticeView(reqVo);
    }

    @PostMapping("/delete")
    public int noticeDelete(@RequestBody NoticeDto reqVo) {
        return noticeService.noticeDelete(reqVo);
    }

    @PostMapping("/update")
    public int noticeUpdate(@RequestBody NoticeDto reqVo) {
        return noticeService.noticeUpdate(reqVo);
    }


}
