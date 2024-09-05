package com.example.demo11.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.SearchDto;

@SpringBootTest
public class MemberMapperTest {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    BookMapper bookMapper;

    @Test
    void testSelectMemberList() {
        SearchDto searchDto = new SearchDto();
        // List<MemberDto> list = memberMapper.selectMemberList(searchDto);
        // assertEquals(10, list.size());
    }
}
