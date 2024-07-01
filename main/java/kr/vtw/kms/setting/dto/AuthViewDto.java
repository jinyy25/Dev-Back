package kr.vtw.kms.setting.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthViewDto {

    private int userSeq;
    private String userId;
    private String userName;
    private String lastLogonTime;
    private String userType;
    private String manType;
    private String downType;
    private String phone;

}
