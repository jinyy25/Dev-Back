package kr.vtw.kms.reference.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDto {

    private int fileCode;
    private String fileName;
    private String titleFile;
    private String fileExt;
    private int viewCnt;
    private int downCnt;
    private String inid;
    private String indt;
    private String userName;
    private String descFile;
    private String saveName;
    private String tmpName;

}
