package kr.vtw.kms.record.controller;


import kr.vtw.kms.record.dto.RecordDto;
import kr.vtw.kms.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/record")
public class RecordController {


    @Autowired
    private RecordService recordService;

    @PostMapping("/view")
    public List<RecordDto> recordView(@RequestBody RecordDto reqVo) {
        return recordService.recordView(reqVo);
    }


    @PostMapping("/detail")
    public RecordDto recordDetail(@RequestBody RecordDto reqVo) {
        return recordService.recordDetail(reqVo);
    }

    @PostMapping("/delete")
    public int recordDelete(@RequestBody RecordDto reqVo) {
        return recordService.recordDelete(reqVo);
    }

    @PostMapping("/update")
    public int recordUpdate(@RequestBody RecordDto reqVo) {
        return recordService.recordUpdate(reqVo);
    }



}
