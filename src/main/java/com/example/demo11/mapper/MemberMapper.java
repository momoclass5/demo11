package com.example.demo11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.SearchDto;

@Mapper
public interface MemberMapper {

    MemberDto login(MemberDto member);

    int insertMember(MemberDto member);

    int selectCheckId(String id);

    int updatePw(MemberDto member);

    List<MemberDto> selectMemberList(SearchDto searchDto);

    int selectTotalCnt();

}
