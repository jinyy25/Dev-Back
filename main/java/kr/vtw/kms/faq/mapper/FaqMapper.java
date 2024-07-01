package kr.vtw.kms.faq.mapper;


import kr.vtw.kms.faq.dto.FaqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqMapper {

    int faqInsert (FaqDto faqVo);

}
