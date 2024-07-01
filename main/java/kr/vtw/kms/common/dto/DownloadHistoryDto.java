package kr.vtw.kms.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadHistoryDto {

    private int historySeq;
    private String userId;
    private int fileCode;
    private String fileName;
    private String filePath;
    private Date downloadTimestamp;
    private String fileExt;
    private String ipAddress;
    private String downloadResult;
    private String errorMessage;

}
