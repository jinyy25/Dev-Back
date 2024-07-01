package kr.vtw.kms.common.mapper;

import kr.vtw.kms.common.dto.DownloadHistoryDto;
import kr.vtw.kms.common.dto.ParserDto;
import kr.vtw.kms.common.dto.SourceDto;
import kr.vtw.kms.common.dto.UploadDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper {

   int fileInsert (UploadDto reqVo);

   ParserDto getTempName(int filecd);

   int insertSource(SourceDto sourceDto);

   int updateFile(int filecd);

   int insertHistory(DownloadHistoryDto downloadHistoryDto);

}
