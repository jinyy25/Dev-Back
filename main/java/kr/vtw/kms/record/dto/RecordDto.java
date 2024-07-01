package kr.vtw.kms.record.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {

    private int fileCode;
    private String titleFile;
    private String saveName;
    private String saveThumbname;
    private String indt;
    private String descFile;
    private String typeFile;
}
