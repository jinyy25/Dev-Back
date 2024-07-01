package kr.vtw.kms.reqdata.mapper;

import kr.vtw.kms.reqdata.dto.ReqResponsDto;
import kr.vtw.kms.reqdata.dto.RequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReqDataMapper {

    List<ReqResponsDto> requestList(RequestDto reqVo);

    int updateApproval(int reqSeq);

    int updateReject(int reqSeq);

    List<ReqResponsDto> retrieveReqInfo(RequestDto reqVo);

    int fileRequest(RequestDto reqVo);

//    List<ReqDataDto> errorList();
}
