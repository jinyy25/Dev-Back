package kr.vtw.kms.setting.controller;

import kr.vtw.kms.setting.dto.AuthSearchResultDto;
import kr.vtw.kms.setting.dto.AuthViewDto;
import kr.vtw.kms.setting.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setting")
public class SettingController {


    @Autowired
    private SettingService settingService;

    @ResponseBody
    @PostMapping("/auth")
    public AuthSearchResultDto settingAuth(@RequestBody AuthViewDto reqVo){
        return settingService.settingAuth(reqVo.getUserName());
    }


    @ResponseBody
    @PostMapping("/authview")
    public AuthViewDto settingView(@RequestBody AuthViewDto reqVo){
        return settingService.settingView(reqVo.getUserSeq());
    }



    @ResponseBody
    @PostMapping("/authsave")
    public int saveSetting(@RequestBody AuthViewDto reqVo){
        return settingService.saveSetting(reqVo);
    }


}
