package com.springboot.lecture.service.impl;

import com.springboot.lecture.common.CommonResponse;
import com.springboot.lecture.configuration.security.JwtTokenProvider;
import com.springboot.lecture.data.dto.SignInResultDto;
import com.springboot.lecture.data.dto.SignUpResultDto;
import com.springboot.lecture.data.entity.User;
import com.springboot.lecture.data.repository.UserRepository;
import com.springboot.lecture.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {

    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String id, String password, String name, String role) {

        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        if (role.equalsIgnoreCase("admin")) {

            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new  SignUpResultDto();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedUser.getName().isEmpty()) {

            LOGGER.info("[getSignUpResult] 정상처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            LOGGER.info("[getSignUpResult] 실패처리 완료");
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] 회원 정보 요청");

        User user = userRepository.findByUid(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        LOGGER.info("[getSignInResult] Id : {}", id);

        LOGGER.info("[signIn] 패스워드 비교 수행");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        LOGGER.info("[signIn] 패스워드 일치");

        String token = jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());

        SignInResultDto signInResultDto = new SignInResultDto(true, 200, "로그인 성공", token);

        LOGGER.info("[signIn] 로그인 성공: id={}, token={}", id, token);

        return signInResultDto;
    }



    private void setSuccessResult(SignUpResultDto result) {

        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {

        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

}
