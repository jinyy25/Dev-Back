package kr.vtw.kms.reqdata.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private Timestamp startDate;
    private Timestamp endDate;
    private String userName;
    private int reqTypeCd;
    private int reqCode;
    private String userId;
    private String reqDesc;
    private String inid;
    private Long userSeq;
    private Long fileCode;
    private int rowCount;

}
