package com.example.demo11.dto;

import lombok.Data;
import lombok.Setter;

/**
 * 페이지처리를 위한 정보
 * 검색어, 검색필드 정보
 * 
 */
@Data
public class SearchDto {
    // 요청 페이지 번호
    private int pageNo = 1;
    // 한 페이지에 보여질 게시물의 수
    private int amount = 10; // 한페이지에 게시물을 10건씩 조회 해서 화면에 출력

    // 검색 필드
    private String searchField = "";
    // 검색어
    private String searchWord = "";
}
