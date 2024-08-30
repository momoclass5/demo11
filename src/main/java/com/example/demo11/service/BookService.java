package com.example.demo11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.mapper.BookMapper;

@Service
public class BookService {

    @Autowired
    BookMapper mapper;

    public List<BookDto> selectBookList(SearchDto searchDto) {
        return mapper.selectBookList(searchDto);
    }

}
