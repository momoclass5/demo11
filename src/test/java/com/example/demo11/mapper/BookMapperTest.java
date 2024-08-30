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

        // 검색필드 설정
        dto.setSearchField("1=1 or title");
        // 검색어 설정
        dto.setSearchWord("입력테스트");

        // 페이징과 검색처리를 위해 SearchDto를 매개변수로 넣어준다
        List<BookDto> list = mapper.selectBookList(dto);
        assertEquals(1, list.size());
    }
}
