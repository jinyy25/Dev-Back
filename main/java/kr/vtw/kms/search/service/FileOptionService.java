package kr.vtw.kms.search.service;

import kr.vtw.kms.search.repository.FileOptionRepository;
import kr.vtw.kms.search.dto.OptionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FileOptionService")
public class FileOptionService {

    @Autowired
    private FileOptionRepository fileOptionInfoRepository;

    public List<String> getOrgnm() {
        return fileOptionInfoRepository.findDistinctOrganiName();
    }

    public List<String> getProjectnm(OptionRequestDto requestVo) { return fileOptionInfoRepository.findDistinctProjectName(requestVo.getSyear(),requestVo.getEyear()); }

    public List<String> getYear() {
        return fileOptionInfoRepository.findYear();
    }


}
