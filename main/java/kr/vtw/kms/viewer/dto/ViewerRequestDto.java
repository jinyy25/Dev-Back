package kr.vtw.kms.viewer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewerRequestDto {

    private Long fileCode;
    private String fileName;
    private String projectYear;
    private String projectName;
    private String saveName;
    private String fileExt;
    private String tmpName;
    private String typeFile;
    private String organiName;
    private String indt;
    private String inid;
    private int reqCode;
    private String userId;


}
