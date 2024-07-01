package kr.vtw.kms.viewer.mapper;

import kr.vtw.kms.viewer.dto.ViewerRequestDto;
import kr.vtw.kms.viewer.dto.ViewerResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FileInfoMapper {
    ViewerResponseDto getFileDetail(ViewerRequestDto requestVo);
}
