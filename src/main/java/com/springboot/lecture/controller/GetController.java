package com.springboot.lecture.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String tea(){
        return "홍차나왔어요!!!";
    }

    // http://localhost:8080/get-api/name
    @GetMapping(value="/name")
    public String getName(){
        return "Jinhee";
    }

}
