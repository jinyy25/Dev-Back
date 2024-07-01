package kr.vtw.kms.search.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequestDto {
    private int fileCode;
    private String userId;
    private String syear ;
    private String eyear ;

}
