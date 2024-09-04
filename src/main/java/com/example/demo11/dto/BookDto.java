package com.example.demo11.dto;

import lombok.Data;

@Data
public class BookDto {
    private String b_no;
    private String title;
    private String author;
    private String price;
    private String rentyn;
    private String delyn;
    private String regdate;
    private String r_no;
    // 메인 이미지 파일 번호
    private int img_f_no;
    private boolean isNew;
}
