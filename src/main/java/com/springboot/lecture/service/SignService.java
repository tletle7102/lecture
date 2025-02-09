package com.springboot.lecture.service;

import com.springboot.lecture.data.dto.SignInResultDto;
import com.springboot.lecture.data.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;

}
