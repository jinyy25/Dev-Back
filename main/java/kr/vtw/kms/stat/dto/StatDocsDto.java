package kr.vtw.kms.stat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatDocsDto {
    private String ext;
    private int totalCnt;
    private int todayCnt;
    private int errorCnt;
    private int fileCnt;
    private int extractCnt;
    private int elasticCnt;
    private int passageCnt;
    private int keywordCnt;
    private int noExtCnt;
    private int extrErrCnt;
    private int fileErrCnt;
}
