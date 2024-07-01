package kr.vtw.kms.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadDto {

    private String filePath;
    private String fileName;
    private String fileExt;
    private Long fileSize;
    private String saveName;
    private String titleFile;
    private String descFile;
    private int typeCode;
    private String typeFile;
    private String indt;
    private String updt;
    private String inid;
    private String upid;
    private String userName;
    private String thumbnailName;
    private String saveThumbname;

}
