package com.springboot.lecture.controller;

import com.springboot.lecture.data.dto.SignInResultDto;
import com.springboot.lecture.data.dto.SignUpResultDto;
import com.springboot.lecture.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/sign-api")
public class SignController {

    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    // 로그인 (Sign In)
    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(
            @RequestParam String id,
            @RequestParam String password
    ) throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도합니다. id: {}, pw: ****", id);
        SignInResultDto signInResultDto = signService.signIn(id, password);

        if (signInResultDto.getCode() == 0) {
            LOGGER.info("[signIn] 로그인 성공. id: {}, token: {}", id, signInResultDto.getToken());
        }
        return signInResultDto;
    }

    // 회원가입 (Sign Up)
    @PostMapping(value = "/sign-up")
    public SignUpResultDto signUp(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String role
    ) {
        LOGGER.info("[signUp] 회원가입 시도. id: {}, name: {}, role: {}", id, name, role);
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);

        LOGGER.info("[signUp] 회원가입 완료. id: {}", id);
        return signUpResultDto;
    }

    // 예외 테스트용 API
    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    // 예외 처리
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("[Exception] 발생: {}, {}", e.getCause(), e.getMessage());

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error_type", httpStatus.getReasonPhrase());
        errorResponse.put("code", "400");
        errorResponse.put("message", "에러 발생");

        return new ResponseEntity<>(errorResponse, responseHeaders, httpStatus);
    }
}

