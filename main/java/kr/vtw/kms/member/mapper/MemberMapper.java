package kr.vtw.kms.member.mapper;

import kr.vtw.kms.member.dto.LoginHistoryDto;
import kr.vtw.kms.member.dto.MemberInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

    int insertLoginHistory(LoginHistoryDto loginHistoryDto);
    int updateLoginHistory(Long userSeq);

    MemberInfoDto getUserInfo(MemberInfoDto memberInfoDto);

    int updateRoleSetting(String userId);
}
