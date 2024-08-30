package com.example.demo11.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.service.BookService;

@Controller
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/book/list")
    public void getMethodName(Model model, SearchDto searchDto) {
        model.addAttribute("title", "도서목록");
        // 도서목록을 조회
        // List<BookDto> list = service.selectBookList(searchDto);

        Map<String, Object> map = service.selectBookList(searchDto);

        // 도서목록을 화면에 전달
        model.addAttribute("map", map);

    }

}
