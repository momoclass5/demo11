// 페이지블럭의 페이지번호를 클릭하면 함수가 실행
// searchForm을 이용해 페이지 요청 정보를 서버에 전달
function go(pageNo){
    console.log(pageNo);
    document.querySelector("#pageNo").value = pageNo;
    // 폼을 전송(요청)
    searchForm.submit();

    
}

// 외부스크립트 문서 작성

// 저장된 쿠키값 읽어오기
function getCookie(name) {
    // 쿠키 문자열을 "; "로 나눕니다. -> 여러개의 쿠키를 배열로 저장
    const cookies = document.cookie.split('; ');
    // 각 쿠키를 순회하면서 이름과 일치하는 쿠키를 찾습니다.
    for (let cookie of cookies) {
        // 키와값을 분리 시켜서 변수에 저장
        const [cookieName, cookieValue] = cookie.split('=');
        // 쿠키 이름이 일치하면 값을 반환합니다.
        if (cookieName === name) {
            return decodeURIComponent(cookieValue);
        }
    }
    // 쿠키가 없으면 null을 반환합니다.
    return null;
}

