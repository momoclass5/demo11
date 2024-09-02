package com.example.demo11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.mapper.MemberMapper;

@Service
public class MemberService {
    @Autowired
    MemberMapper mapper;

    public Map<String, Object> selectMemberList(SearchDto searchDto) {
        Map<String, Object> map = new HashMap<>();

        // 페이지블럭을 생성 - pageDto
        // searchDto : pageNo(요청 페이지번호), amount(페이지당 보여줄 게시물수)
        // 사용자가 값을 전달 하지 않으면 필드의 초기값으로 초기화가 됨
        // totalCnt(총건수)
        List<MemberDto> list = mapper.selectMemberList(searchDto);
        map.put("list", list);

        int totalCnt = mapper.selectTotalCnt();
        PageDto pageDto = new PageDto(searchDto, totalCnt);
        map.put("pageDto", pageDto);
        return map;
    }
}
