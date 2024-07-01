package kr.vtw.kms.record.service;


import kr.vtw.kms.record.dto.RecordDto;
import kr.vtw.kms.record.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service("RecordService")
public class RecordService {

    @Autowired
    public RecordMapper mapper;

    @Value("${reference.path}")
    private String videoPath;


    public List<RecordDto> recordView(RecordDto reqVo){
        List<RecordDto> temp=mapper.recordView(reqVo);
        try {
            for (RecordDto recordDto : temp) {
                String fileName = recordDto.getSaveThumbname();
                Path imagePath = Paths.get(videoPath, fileName);

                if (Files.exists(imagePath)) {
                    byte[] imageBytes = Files.readAllBytes(imagePath);
                    String base64Encoded = Base64Utils.encodeToString(imageBytes);
                    String imgSource = "data:image/png;base64," + base64Encoded;
                    recordDto.setSaveThumbname(imgSource);
                } else {
                    recordDto.setSaveThumbname("");
                }
            }
            return temp;
        }catch(IOException e){
            e.printStackTrace();
            return null; // 이미지 읽기 실패
        }
    }




    public RecordDto recordDetail(RecordDto reqVo){

        return mapper.recordDetail(reqVo);
    }

    public int recordDelete(RecordDto reqVo){
        return mapper.recordDelete(reqVo);
    }
    public int recordUpdate(RecordDto reqVo){
        return mapper.recordUpdate(reqVo);
    }


}
