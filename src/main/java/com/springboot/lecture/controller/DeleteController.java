package com.springboot.lecture.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    @DeleteMapping(value = "/{PathVariable}")
    public String DeleteVariable(
            @PathVariable("PathVariable") String variable){
        return variable;
    }

    @DeleteMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam("emailVariable") String emailVariable){
        return "emailVariable : " + emailVariable;
    }

}
