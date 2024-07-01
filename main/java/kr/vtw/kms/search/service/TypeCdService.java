package kr.vtw.kms.search.service;

import kr.vtw.kms.search.repository.TypeCdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TypeCdService")
public class TypeCdService {

    @Autowired
    private TypeCdRepository searchOptionRepository;

    public List<String> getStype() {
        return searchOptionRepository.findDistinctTypeNm();
    }
}
