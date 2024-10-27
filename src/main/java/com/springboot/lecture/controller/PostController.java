package com.springboot.lecture.controller;

import com.springboot.lecture.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample(){
        log.info("Hello 포스트API  요쳥했어요~");
        return "Hello Post API";
    }

    @PostMapping(value = "/member")
    public String postMember(
            @RequestBody Map<String,Object> postData){
        StringBuilder sb = new StringBuilder();
        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    @PostMapping(value = "/member2")
    public String postMemberDto(
            @RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

}
