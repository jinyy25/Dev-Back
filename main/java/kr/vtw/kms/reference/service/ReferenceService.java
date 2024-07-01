package kr.vtw.kms.reference.service;

import kr.vtw.kms.reference.dto.MileageDto;
import kr.vtw.kms.reference.dto.ReferenceDto;
import kr.vtw.kms.reference.mapper.ReferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("ReferenceService")
public class ReferenceService {

    @Autowired
    private ReferenceMapper mapper;

    public List<ReferenceDto> referenceSearch(ReferenceDto reqVo) {
        return mapper.referenceSearch(reqVo);
    }
    public ReferenceDto referenceDetail(ReferenceDto reqVo) {
        return mapper.referenceDetail(reqVo);
    }

    public int referenceDelete(ReferenceDto reqVo) {
        return mapper.referenceDelete(reqVo);
    }


    public int referenceUpdate(ReferenceDto reqVo) {
        return mapper.referenceUpdate(reqVo);
    }

    public int viewCount(ReferenceDto reqVo) {
        return mapper.viewCount(reqVo);
    }



    public int downloadCount(ReferenceDto reqVo) {
        int downloadCount = mapper.downloadCount(reqVo);
        MileageDto mileageDto = new MileageDto();
        if(downloadCount ==1){
            mileageDto.setUserId(reqVo.getInid());
            mileageDto.setMileage(2);
            mileageDto.setMileageCategory("download");
        }

        return mapper.insertMileage(mileageDto);
    }




}
