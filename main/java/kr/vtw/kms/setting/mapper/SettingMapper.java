package kr.vtw.kms.setting.mapper;


import kr.vtw.kms.setting.dto.AuthDetailDto;
import kr.vtw.kms.setting.dto.AuthViewDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SettingMapper {

    List<AuthViewDto> settingAuth(String userName);

    AuthViewDto settingView(int userSeq);

    int saveSetting(AuthDetailDto reqVo);

    int settingCount();

    int updateDuration(int userSeq, LocalDateTime state);
}
