package kr.vtw.kms.stat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatDownloadDto {
    private String userId;
    private String userName;
    private int fileCode;
    private String projectName;
    private String downloadTimestamp;
    private String ipAddress;
    private String fileName;
    private String roleDuration;
    private String fileExt;
}
