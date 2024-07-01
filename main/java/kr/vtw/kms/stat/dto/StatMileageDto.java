package kr.vtw.kms.stat.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatMileageDto {
    private String userName;
    private int totalMileage;
    private int downCount;
    private int uploadCount;
}
