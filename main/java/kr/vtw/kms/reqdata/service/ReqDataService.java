package kr.vtw.kms.reqdata.service;

import kr.vtw.kms.reqdata.dto.ReqResponsDto;
import kr.vtw.kms.reqdata.dto.RequestDto;
import kr.vtw.kms.reqdata.mapper.ReqDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReqDataService")
public class ReqDataService {

    @Autowired
    private ReqDataMapper mapper;

    public List<ReqResponsDto> requestList(RequestDto reqVo) {
        return mapper.requestList(reqVo);
    }

    public int updateApproval(List<Integer> reqList) {

        int result=0;
        for(int reqSeq :  reqList){
            result = mapper.updateApproval(reqSeq);
            result++;
        }

        return result;
    }

    public int updateReject(List<Integer> reqList) {

        int result=0;
        for(int reqSeq :  reqList){
            result = mapper.updateReject(reqSeq);
            result++;
        }

        return result;
    }

    public List<ReqResponsDto> retrieveReqInfo(RequestDto reqVo) {
        return mapper.retrieveReqInfo(reqVo);
    }

    public int fileRequest(RequestDto reqVo) {
        return mapper.fileRequest(reqVo);
    }

    public int batchRequest(List<RequestDto> reqList) {

        int result=0;
        for(RequestDto reqData : reqList){
            result = mapper.fileRequest(reqData);
            result++;
        }

        return result;
    }


//    public List<ReqDataDto> errorList() {
//        return mapper.errorList();
//    }

}
