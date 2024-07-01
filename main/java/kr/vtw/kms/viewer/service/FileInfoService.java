package kr.vtw.kms.viewer.service;


import kr.vtw.kms.viewer.dto.ViewerRequestDto;
import kr.vtw.kms.viewer.dto.ViewerResponseDto;
import kr.vtw.kms.viewer.mapper.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FileInfoService")
public class FileInfoService {

    @Autowired
    public FileInfoMapper mapper;

    public ViewerResponseDto getFileDetail(ViewerRequestDto requestVo){
        return  mapper.getFileDetail(requestVo);
    }

}
