package com.example.demo11.dto;

import lombok.Data;

@Data
public class PageDto {
    // 페이지 블럭에 출력할 시작번호
    private int startPage = 1;
    // 페이지 블럭에 출력학 끝번호
    private int endPage = 5;

    private boolean prev = false;
    private boolean next = true;

}
