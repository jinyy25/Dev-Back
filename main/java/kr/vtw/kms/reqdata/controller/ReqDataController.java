package kr.vtw.kms.reqdata.controller;

import kr.vtw.kms.reqdata.dto.ReqResponsDto;
import kr.vtw.kms.reqdata.dto.RequestDto;
import kr.vtw.kms.reqdata.service.ReqDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reqdata")
public class ReqDataController {

    @Autowired
    private ReqDataService reqDataService;

    @PostMapping("/request-list")
    public List<ReqResponsDto> requestList(@RequestBody RequestDto reqVo) {
        return reqDataService.requestList(reqVo);
    }

    @PostMapping("/approval-request-list")
    public List<ReqResponsDto> approvalRequestList(@RequestBody RequestDto reqVo) {
        return reqDataService.requestList(reqVo);
    }

    /*일괄승인*/
    @PostMapping("/approval")
    public int updateApproval(@RequestBody Map<String, List<Integer>> reqListMap) {
        List<Integer> reqList = reqListMap.get("reqSeqList");
        return reqDataService.updateApproval(reqList);
    }

    /*일괄반려*/
    @PostMapping("/reject")
    public int updateReject(@RequestBody Map<String, List<Integer>> reqListMap) {
        List<Integer> reqList = reqListMap.get("reqSeqList");
        return reqDataService.updateReject(reqList);
    }

    @PostMapping("/approval-list")
    public List<ReqResponsDto> retrieveReqInfo(@RequestBody RequestDto reqVo) {
        return reqDataService.retrieveReqInfo(reqVo);
    }

    @PostMapping("/request")
     public int fileRequest(@RequestBody RequestDto reqVo) {
         return reqDataService.fileRequest(reqVo);
     }


    @PostMapping("/batch-request")
     public int batchRequest(@RequestBody Map<String, List<RequestDto>> reqListMap) {
        List<RequestDto> reqList = reqListMap.get("rowDataList");
        int result=reqDataService.batchRequest(reqList);
        return result;
     }

//    @GetMapping("/errorlist")
//    public List<ReqDataDto> errorList() {
//        return reqDataService.errorList();
//    }



}
