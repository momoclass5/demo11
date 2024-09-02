package com.example.demo11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.SearchDto;

@Mapper
public interface MemberMapper {
    /**
     * 사용자 정보를 조회
     * 
     * @param searchDto : 검색조건및 페이지정보
     * @return List<MemberDto> : 사용자목록
     */
    public List<MemberDto> selectMemberList(SearchDto searchDto);
}
