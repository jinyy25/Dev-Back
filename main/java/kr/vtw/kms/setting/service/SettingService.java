package kr.vtw.kms.setting.service;

import kr.vtw.kms.setting.dto.AuthDetailDto;
import kr.vtw.kms.setting.dto.AuthSearchResultDto;
import kr.vtw.kms.setting.dto.AuthViewDto;
import kr.vtw.kms.setting.mapper.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("SettingService")
public class SettingService {


    @Autowired
    public SettingMapper mapper;

    public AuthSearchResultDto settingAuth (String userName){
        List<AuthViewDto> authList = mapper.settingAuth(userName);
        int count = settingCount();
        return new AuthSearchResultDto(count, authList);
    }

    public AuthViewDto settingView (int userSeq){
        return mapper.settingView(userSeq);
    }

    public int saveSetting (AuthViewDto reqVo){

        AuthDetailDto convertType = new AuthDetailDto();
        convertType.setUserSeq(reqVo.getUserSeq());
        switch(reqVo.getUserType()){
            case "사용자": convertType.setUserType("ROLE_USER");
            break;
            case "관리자": convertType.setUserType("ROLE_ADMIN");
            break;
            default:
            break;
        }

        switch(reqVo.getManType()){
            case "권한없음": convertType.setManType("NONE");
            break;
            case "인력조회자": convertType.setManType("ROLE_VIEW");
            break;
            case "인력관리자": convertType.setManType("ROLE_MANAGE");
            break;
            default:
            break;
        }

        switch(reqVo.getDownType()){
            case "권한없음": convertType.setDownType("NONE");
            break;
            case "다운권한": convertType.setDownType("ROLE_DOWNLOAD");
            break;
            case "승인권한": convertType.setDownType("ROLE_APPROVAL");
            break;
            case "전체권한": convertType.setDownType("ROLE_ALL");
            break;
            default:
            break;
        }
        String downType = convertType.getDownType();
        if(downType.equals("ROLE_ALL")||downType.equals("ROLE_DOWNLOAD")){
           mapper.updateDuration(reqVo.getUserSeq(), LocalDateTime.now());
        }else{
            mapper.updateDuration(reqVo.getUserSeq(), null);
        }

        return mapper.saveSetting(convertType);
    }

    public int settingCount(){
        return mapper.settingCount();
    }







}
