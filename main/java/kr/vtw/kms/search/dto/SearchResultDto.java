package kr.vtw.kms.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDto {

    private long totalHits;
    private List<TempDto> searchResults;

}
