package com.springboot.lecture.configuration.security;// SecurityConfiguration

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider, CustomAccessDeniedHandler accessDeniedHandler, CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(basic -> basic.disable()) // 기본 HTTP Basic 인증 비활성화

                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화 (JWT 사용할 경우, 쿠키 기반 세션이 아니므로)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 사용 시 STATELESS 설정
                )

                .authorizeHttpRequests(auth -> auth
                        // Swagger 및 예외 관련 요청 허용
                        .requestMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html",
                                "/webjars/**", "/swagger/**", "/sign-api/exception").permitAll()

                        // 회원가입 및 로그인 관련 요청 허용
                        .requestMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()

                        // 특정 GET 요청 허용
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()

                        // 예외 처리 관련 요청 허용
                        .requestMatchers("/**/exception").permitAll()

                        // ADMIN 권한이 필요한 요청 (모든 요청)
                        .anyRequest().hasRole("ADMIN")
                )

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler) // 권한이 없는 요청 처리
                        .authenticationEntryPoint(authenticationEntryPoint) // 인증 실패 처리
                )

                .formLogin(login -> login.disable()) // 기본 로그인 폼 비활성화

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
        ;

        return http.build();
    }
}
