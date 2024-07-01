package kr.vtw.kms.reference.controller;


import kr.vtw.kms.reference.service.ReferenceService;
import kr.vtw.kms.reference.dto.ReferenceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reference")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @PostMapping("/search")
    public List<ReferenceDto> referenceSearch(@RequestBody ReferenceDto reqVo) {
        return referenceService.referenceSearch(reqVo);
    }

    @PostMapping("/detail")
    public ReferenceDto referenceDetail(@RequestBody ReferenceDto reqVo) {
        return referenceService.referenceDetail(reqVo);
    }

    @PostMapping("/update")
    public int referenceUpdate(@RequestBody ReferenceDto reqVo) {
        return referenceService.referenceUpdate(reqVo);
    }

    @PostMapping("/delete")
    public int referenceDelete(@RequestBody ReferenceDto reqVo) {
        return referenceService.referenceDelete(reqVo);
    }

    @PostMapping("/viewCount")
     public int viewCount(@RequestBody ReferenceDto reqVo) {
         return referenceService.viewCount(reqVo);
     }

    @PostMapping("/downloadCount")
       public int downloadCount(@RequestBody ReferenceDto reqVo) {
           return referenceService.downloadCount(reqVo);
       }
}
