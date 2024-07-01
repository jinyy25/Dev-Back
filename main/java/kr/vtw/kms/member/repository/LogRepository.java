package kr.vtw.kms.member.repository;

import kr.vtw.kms.member.entity.tb_log_vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<tb_log_vendor, Long> {


}

