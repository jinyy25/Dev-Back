package kr.vtw.kms.member.repository;

import kr.vtw.kms.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}
