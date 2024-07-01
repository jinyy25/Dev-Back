package kr.vtw.kms.reqdata.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqResponsDto {

    private int reqSeq;
    private String reqCode;
    private int fileCode;
    private String reqDesc;
    private String indt;
    private String updt;
    private String fileExt;
    private String fileName;
    private String saveName;
    private String userName;
}
