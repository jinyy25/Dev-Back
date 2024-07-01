package kr.vtw.kms.stat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatFileDto {

    private String fileName;
    private String projectName;
    private String fileExt;
    private int reqTotal;
    private int reqConfirm;
    private int reqReject;
    private int reqRun;
    private int reqCancel;
    private int fileCode;

}
