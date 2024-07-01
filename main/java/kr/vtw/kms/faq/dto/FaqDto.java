package kr.vtw.kms.faq.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaqDto {

    private String faqWriter;
    private String faqEmail;
    private String faqDesc;
    private String faqTitle;

}
