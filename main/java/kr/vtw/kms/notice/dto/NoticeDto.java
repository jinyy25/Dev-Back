package kr.vtw.kms.notice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private String boardId;
    private String title;
    private String writer;
    private String indt;
    private String updt;
    private String content;

}
