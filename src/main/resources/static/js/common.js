// 페이지블럭의 페이지번호를 클릭하면 함수가 실행
// searchForm을 이용해 페이지 요청 정보를 서버에 전달
function go(pageNo){
    console.log(pageNo);
    document.querySelector("#pageNo").value = pageNo;
    // 폼을 전송(요청)
    searchForm.submit();

    
}