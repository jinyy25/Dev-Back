package kr.vtw.kms.security.config;

import kr.vtw.kms.security.jwt.JwtAccessDeniedHandler;
import kr.vtw.kms.security.jwt.JwtAuthenticationEntryPoint;
import kr.vtw.kms.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    //비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //REST API를 통해 세션 없이 토큰으로 데이터를 주고받음

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)// 로그인 페이지 : /auth/**를 제외한 모든 uri의 request는 토큰 필요

            .and()
            .authorizeRequests()

            .antMatchers("/login/**").permitAll()
                .antMatchers("/**").permitAll()

            .antMatchers("/error").permitAll()
            .antMatchers("/api/search/elasticsearch").permitAll()

            .antMatchers("/api/videos/**").permitAll()
            .antMatchers("/api/view/**").permitAll()
            .antMatchers("/profile/**").permitAll()

            .antMatchers("/api/setting/**").hasRole("ADMIN")
            .antMatchers("/api/stat/**").hasRole("ADMIN")

            .antMatchers("/api/reqdata/approval/**").hasRole("ADMIN")
            .antMatchers("/api/reqdata/reject/**").hasRole("ADMIN")
            .antMatchers("/api/reqdata/request/**").permitAll()

            .antMatchers("/api/notice/insert/**").hasRole("ADMIN")
            .antMatchers("/api/notice/delete/**").hasRole("ADMIN")
            .antMatchers("/api/notice/update/**").hasRole("ADMIN")

            .antMatchers("/api/record/delete/**").hasRole("ADMIN")
            .antMatchers("/api/record/update/**").hasRole("ADMIN")

            .antMatchers("/public/**").permitAll()
            .antMatchers("/public/images/**").permitAll()
            .antMatchers("/api/pdf/pdfview/**").permitAll()

            .antMatchers("/file/download/**").permitAll()
            .antMatchers("/file/downloadMulti/**").permitAll()
            .antMatchers("/file/delete/**").permitAll()

            .anyRequest().authenticated()//tokenProvider 적용

            .and()
            .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
