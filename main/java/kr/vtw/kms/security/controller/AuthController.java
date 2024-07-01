package kr.vtw.kms.security.controller;

import kr.vtw.kms.member.dto.MemberRequestDto;
import kr.vtw.kms.security.dto.TokenDto;
import kr.vtw.kms.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/auth/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenDto> login(@RequestBody TokenDto requestDto) {
        return ResponseEntity.ok(authService.refresh(requestDto));
    }


}
