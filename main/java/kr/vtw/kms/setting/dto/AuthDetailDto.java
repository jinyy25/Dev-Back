package kr.vtw.kms.setting.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDetailDto {

    private int userSeq;
    private String userType;
    private String manType;
    private String downType;

}
