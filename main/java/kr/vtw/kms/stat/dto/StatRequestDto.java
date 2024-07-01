package kr.vtw.kms.stat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatRequestDto {
    private Timestamp reqSdate;
    private Timestamp reqEdate;
    private String reqStype;
    private String userName;
}
