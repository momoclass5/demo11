package com.example.demo11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo11.dto.SearchDto;

@Controller
public class MemberController {

    @GetMapping("/member/list")
    public void getMethodName(SearchDto searchDto) {

    }

}
