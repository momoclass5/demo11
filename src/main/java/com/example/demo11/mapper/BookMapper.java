package com.example.demo11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;

@Mapper
public interface BookMapper {
    List<BookDto> selectBookList(SearchDto search);

    int selectTotalCnt();
}
