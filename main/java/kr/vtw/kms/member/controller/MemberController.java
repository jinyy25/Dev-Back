package kr.vtw.kms.member.controller;

import kr.vtw.kms.member.dto.MemberInfoDto;
import kr.vtw.kms.member.dto.MemberResponseDto;
import kr.vtw.kms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/users/login")
    public ResponseEntity<MemberResponseDto> login(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyInfoBySecurity(request));
      }


    @PostMapping("/api/users/profile")
    public MemberInfoDto getUserInfo(@RequestBody MemberInfoDto memberInfoDto) {
     return memberService.getUserInfo(memberInfoDto);
    }
}
