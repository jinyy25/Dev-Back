package kr.vtw.kms.search.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElasticDto {

    private String reqOrg;
    private String reqSearchContent;
    private String reqProjectNm;
    private String reqStype;
    private String reqStartYear;
    private String reqEndYear;
    private String fileExtForm;
    private String reqStypeCd;
    private int firstRow;
    private int lastRow;


}
