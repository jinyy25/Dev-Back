package kr.vtw.kms.search.repository;

import kr.vtw.kms.search.dto.FileInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileOptionRepository extends JpaRepository<FileInfoDto, Long> {

    @Query("SELECT DISTINCT organiName FROM FileInfoDto Order by organiName")
    List<String> findDistinctOrganiName();

    @Query("SELECT DISTINCT TRIM(projectName) FROM FileInfoDto WHERE projectYear BETWEEN :syear AND :eyear Order by projectName")
    List<String> findDistinctProjectName(@Param("syear") String syear,@Param("eyear") String eyear);

    @Query("SELECT projectYear FROM FileInfoDto GROUP BY projectYear")
    List<String> findYear();

}

