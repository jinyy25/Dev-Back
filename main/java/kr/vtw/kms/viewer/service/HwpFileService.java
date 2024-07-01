package kr.vtw.kms.viewer.service;


import org.springframework.stereotype.Service;

@Service("HwpFileService")
public class HwpFileService {

    //이미지파일 추출
    public String getImageExtension(String imageName) {
        int lastDotIndex = imageName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return imageName.substring(lastDotIndex + 1);
        }
        return "png";
    }


}
