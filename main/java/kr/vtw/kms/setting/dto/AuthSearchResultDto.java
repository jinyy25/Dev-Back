package kr.vtw.kms.setting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthSearchResultDto {

    private int searchCount;
    private List<AuthViewDto> searchResults;

}
