package com.example.demo11.dto;

import lombok.Data;

/*
 *  페이지 블럭을 그리는데 필요한 데이터를 보관
 */
@Data
public class PageDto {

    // 페이지 블럭을 그리는데 필요한 데이터===========
    // 페이지 블럭에 출력할 시작번호
    private int startPage = 1;
    // 페이지 블럭에 출력학 끝번호
    private int endPage = 5;
    // 앞으로 가기버튼을 보여줄지 여부
    private boolean prev = false;
    // 뒤로가기 버튼을 보여줄지 여부
    private boolean next = true;
    // ==============================================

    // 데이터를 추출해내기위해 필요한 데이터 ==========
    private SearchDto searchDto;
    private int totalCnt;
    // =================================================

    public PageDto(SearchDto searchDto, int totalCnt) {
        // 거의 고정
        int pageBlockAmount = 5;

        // 화면에 페이지정보를 출력하기 위해 매개변수로 전달된 searchDto를 필드에 저장
        this.searchDto = searchDto;

        endPage = (int) Math.ceil(searchDto.getPageNo() * 1.0 / pageBlockAmount) * pageBlockAmount;
        startPage = endPage - (pageBlockAmount - 1);

        // 삼항연산자
        // (조건) ? (참일때 반환) : (거짓일때 반환)
        prev = startPage == 1 ? false : true;

        int realEndPage = (int) Math.ceil(totalCnt * 1.0 / searchDto.getAmount());
        endPage = realEndPage < endPage ? realEndPage : endPage;

        next = realEndPage > endPage ? true : false;
    }

}
