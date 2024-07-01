package kr.vtw.kms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequestDto {

    private int type;
    private String fileName;
    private int fileCode;
    private String userId;
    private String projectName;
    private String fileExt;
    private List<String> pptxArray;

}
