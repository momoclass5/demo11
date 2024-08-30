package com.example.demo11.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    BookMapper mapper;

    @Test
    void testSelectBookList() {
        SearchDto dto = new SearchDto();
        // 페이징과 검색처리를 위해 SearchDto를 매개변수로 넣어준다
        List<BookDto> list = mapper.selectBookList(dto);
        assertEquals(dto.getAmount(), list.size());
    }
}
