package kr.vtw.kms.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pp_type_cd")
public class TypeCdDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_cd", nullable = false)
    private Long typeCd;

    @Column(name = "type_nm", nullable = false)
    private String typeNm;

}
