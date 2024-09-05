
// 자바스크립트에서는 타입을 주지 않아도 됨 => 값 자체가 타입이 되므로
// function 함수이름(매개변수){}
function fn_login(){
    console.log('로그인버튼 클릭!!!');
    // /member/loginAction

    // 유효성 겁사 - 사용자의 입력값이 유효한지 체크
    let id = document.querySelector('#id');
    if(id.value == ''){
        alert('아이디를 입력해주세요');
        return;
    }
    let pw = document.querySelector('#pw').value;
    if(pw == ''){
        alert('비밀번호를 입력해주세요');
        return;
    }

    // 폼을 전송(서버에 새로운 요청)
    // 폼이름.submit();
    // method, action에 해서 서버에 요청
    form_login.submit();
}

window.addEventListener('load', ()=>{
    let idSave = getCookie('IdSave');
    // 쿠키가 존재 = 아이가 저장되어 있다고 판단
    if(idSave!=null){
        let id = document.querySelector('#id');
        // 아이디를 화면에 출력
        id.value = idSave;
        
        let chkIdSave = document.querySelector("#chkIdSave");
        // 체크박스에 체크
        chkIdSave.checked = true;
    }
    
})