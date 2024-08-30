package com.example.demo11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.mapper.BookMapper;

@Service
public class BookService {

    @Autowired
    BookMapper mapper;

    public Map<String, Object> selectBookList(SearchDto searchDto) {
        Map<String, Object> map = new HashMap<>();

        // 리스트 조회
        List<BookDto> list = mapper.selectBookList(searchDto);
        map.put("list", list);

        // 페이지 DTO
        int totalCnt = mapper.selectTotalCnt(searchDto);
        PageDto pageDto = new PageDto(searchDto, totalCnt);
        map.put("pageDto", pageDto);

        return map;
    }

}
