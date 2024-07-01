package kr.vtw.kms.search.repository;

import kr.vtw.kms.search.dto.TypeCdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeCdRepository extends JpaRepository<TypeCdDto, String> {

    @Query("SELECT DISTINCT typeNm,typeCd FROM TypeCdDto")
        List<String> findDistinctTypeNm();
}
