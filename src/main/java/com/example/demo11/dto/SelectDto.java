package com.example.demo11.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 리스트를 조회 할때 필요한 데이터를 수집
 */
@Data
@Component
public class SelectDto {
    // 요청페이지번호
    private int pageNo = 1;
    // 한 페이지에 보여질 게시물의 수
    private int amount = 10;
}
