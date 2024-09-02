package com.example.demo11.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
    /*
     * IOC컨테이너에 미리 생성되어 있는 Bean 객체를 주입 받는다
     * DI : 의존성 주입
     * 
     * 객체가 다른 객체를 사용하는경우, 다른객체에 의존적이라고 표현
     */
    @Autowired
    MemberService service;

    @GetMapping("/member/list")
    public void getMethodName(SearchDto searchDto, Model model) {
        // 리스트, pageDto를 담은 Map을 model에 저장하여 화면에 전달
        Map<String, Object> map = service.selectMemberList(searchDto);
        log.info("map : " + map);
        model.addAttribute("map", map);

    }

}
