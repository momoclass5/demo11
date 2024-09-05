package com.example.demo11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SelectDto;
import com.example.demo11.mapper.MemberMapper;

@Service
public class MemberService {

    /**
     * 자동주입받기 위해서는 Bean 으로 등록하는 작업이 필요!!!
     */

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    MemberMapper mapper;

    /**
     * 비밀번호 암호화
     * - 회원가입시 비밀번호 암호화로직이 추가
     * - 로그인시 비밀번호 일치 확인 로직이 추가
     * 
     * @param member
     * @return
     */
    public MemberDto login(MemberDto member) {
        MemberDto loginMember = mapper.login(member);

        // 사용자의 비밀번호 확인
        if (encoder.matches(member.getPw(), loginMember.getPw())) {
            return loginMember;
        } else {
            return null;
        }

        // return mapper.login(member);
    }

    public int insertMember(MemberDto member) {
        // 비밀번호를 암호화 하여 저장 할수 있도록 암호화 로직을 추가
        String encodePw = encoder.encode(member.getPw());
        member.setPw(encodePw);
        return mapper.insertMember(member);
    }

    public int selectCheckId(String id) {
        return mapper.selectCheckId(id);
    }

    public Map<String, Object> selectMemberList(SelectDto selectDto) {
        Map<String, Object> map = new HashMap();

        // 리스트 조회
        List<MemberDto> list = mapper.selectMemberList(selectDto);
        // 페이지 블럭을 그리기 위해 총건수를 조회
        int totalCnt = mapper.selectTotalCnt();

        // 페이지 블럭을 그리는 객체를 생성
        // PageDto pageDto = new PageDto(selectDto, totalCnt);
        map.put("list", list);
        // map.put("pageDto", pageDto);

        return map;
    }

    // 패키지 수정
    // pageDto 주석
}
