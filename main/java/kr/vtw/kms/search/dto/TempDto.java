package kr.vtw.kms.search.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TempDto {

    private Integer fileCode;
    private String projectName;
    private String organiName;
    private String fileName;
    private String typeFile;
    private String projectYear;
    private int upperIndex;
//    private int index;
    private int upperCode;
    private Float score;

    @Override
    public String toString() {
        return "SearchResult{" +
                "fileCode=" + fileCode +
                ", projectName='" + projectName + '\'' +
                ", organiName='" + organiName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", typeFile='" + typeFile + '\'' +
                ", projectYear='" + projectYear + '\'' +
                ", upperCode='" + upperCode + '\'' +
                ", upperIndex='" + upperIndex + '\'' +
//                ", index='" + index + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

}
