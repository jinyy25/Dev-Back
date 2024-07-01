package kr.vtw.kms.ask.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AskDto {
    private String askId;
    private String askWriter;
    private String askEmail;
    private String askDesc;
    private String askTitle;
    private String indt;
    private String updt;
    private String category;

}
