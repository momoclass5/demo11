package com.example.demo11.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.service.BookService;
import com.example.demo11.service.UploadService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Controller
@Slf4j
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/book/list")
    public void getMethodName(Model model, SearchDto searchDto) {
        // 파라메터 수집이 잘 되는지 확인!
        log.info(searchDto.toString());
        model.addAttribute("title", "도서목록");

        // 도서목록을 조회
        // List<BookDto> list = service.selectBookList(searchDto);
        Map<String, Object> map = service.selectBookList(searchDto);

        // 도서목록을 화면에 전달
        model.addAttribute("map", map);
    }

    // 등록페이지로 이동
    @GetMapping("/book/write")
    public void getMethodName() {
    }

    @Autowired
    UploadService uploadService;

    /**
     * 파일을 선택하지 않은경우, 파일객체는 null
     * required = false 가 설정 되지 않으면 오류 발생
     * 
     * @param book
     * @param files
     * @return
     */
    // DB 등록
    @PostMapping("/book/writeAction")
    public String postMethodName(BookDto book,
            @RequestPart(name = "file", required = false) List<MultipartFile> files) {
        System.out.println("======== 전달된 파라메터 정보");
        log.info(book.toString());
        // 파일을 선택 하지 않으면 null이 반환되어져서
        // 로그로 출력시 오류가 발생
        System.out.println(files);

        if (files != null && files.size() > 0) {
            // 파일테이블의 시퀀스값
            int f_no = uploadService.insertUpload(files, "book");
        }

        return "/book/write";
        // return "redirect:/book/list";
    }

}
