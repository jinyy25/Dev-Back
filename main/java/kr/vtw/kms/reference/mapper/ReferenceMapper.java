package kr.vtw.kms.reference.mapper;

import kr.vtw.kms.reference.dto.MileageDto;
import kr.vtw.kms.reference.dto.ReferenceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ReferenceMapper {

    List<ReferenceDto> referenceSearch(ReferenceDto reqVo);

    ReferenceDto referenceDetail(ReferenceDto reqVo);

    int referenceDelete(ReferenceDto reqVo);

    int referenceUpdate(ReferenceDto reqVo);

    int viewCount(ReferenceDto reqVo);

    int downloadCount(ReferenceDto reqVo);

    int insertMileage(MileageDto reqVo);

}
