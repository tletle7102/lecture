package com.springboot.lecture.controller;


import com.springboot.lecture.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/get-api")
public class GetController {

    // http://localhost:8080/get-api/call-cafe
    @RequestMapping(value = "/call-cafe", method = RequestMethod.GET)
    public String coffee(){
        return "커피나왔어요!!!";
    }

    // http://localhost:8080/get-api/korea-love
    @RequestMapping(value = "/korea-love", method = RequestMethod.GET)
    public String tea() {
        return "홍차나왔어요!!!";
    }

    // http://localhost:8080/get-api/name
    @GetMapping(value="/name")
    public String getName() {
        return "Jinhee";
    }

    // http://localhost:8080/get-api/variable1/{variable}
    @GetMapping(value="/variable1/{variable}")
    public String getPathVariableFromRequest(@PathVariable("variable") String variable) {
        return variable;
    }

    // http://localhost:8080/get-api/request1?name={name}&email={email}&organization={organization}
    @GetMapping(value="/request1")
    public String getRequestParam1(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("organization") String organization) {
        return name + " " + email + " " + organization;
    }

    // http://localhost:8080/get-api/request2?key1={key1}&key2={key2}
    @GetMapping(value="/request2")
    public String getRequestParam2 (@RequestParam Map<String, String> param) {
            StringBuilder sb = new StringBuilder();
            param.entrySet().forEach( map -> {
                sb.append(map.getKey() + " : " + map.getValue() + "\n");
            });
            return sb.toString();
    }
    @GetMapping(value="/request3")
    public String getRequestParam3(MemberDto memberDto){

        return memberDto.toString();
    }

}
