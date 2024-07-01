package kr.vtw.kms.reference.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MileageDto {
    private String userId;
    private String mileageCategory;
    private int mileage;
}
