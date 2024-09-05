
function fn_register(){
    //alert("test"); 페이지 연결 확인
    // 유효성검사
    let id = document.querySelector("#id");
    if(id.value == ''){
        alert('아이디를 입력해주세요');
        return;
    }
    if(checkId.value == '0'){
        alert('아이디 중복 검사를 진행 해주세요.');
        return;
    }
    let name = document.querySelector("#name");
    if(name.value == ''){
        alert('이름을 입력해주세요');
        return;
    }
    let pw = document.querySelector("#pw");
    if(pw.value == ''){
        alert('비밀번호를 입력해주세요');
        return;
    }
    // 정규식을 이용하여 비밀번호의 패턴을 체크
    // 정규식 패턴을 작성 할때에는 /로 감싸줍니다.
    let regexp = /^(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,12}$/;
    // test()메서드를 이용하여 정규식 패턴에 일치 하는지 확인 
    if(!regexp.test(pw.value)){
        alert('비밀번호를 영어 대소문자와 특수문자로 8자리 이상 12자리 이하로 입력 해주세요');
        return;
    }

    let pw_check = document.querySelector("#pw_check");
    if(pw.value != pw_check.value){
        alert('비밀번호가 일치하지 않습니다. \n비밀번호를 확인해주세요');
        return;
    }
    // 폼 전송 ( 폼이름.submit() )
    registerForm.submit();
}


// 화면이 로드된 이후 아이디 중복체크 버튼에 클릭 이벤트를 부여
window.addEventListener('load', function(){
    // 텍스트 필드에 변화가 있으면 함수를 실행
    id.addEventListener('change', function(){
        console.log('change')
        // 아이디를 변경할 경우 중복체를 다시 진행 할수 있도록 처리
        checkId.value='0';
        msgbox.innerHTML = '';
        msgbox.classList.add('msgbox')
    })

    btn_checkId.addEventListener('click', function(e){
        // 서브밋 이벤트를 초기화
        e.preventDefault();
        let id = document.querySelector("#id").value;
        if(id == ''){
            alert('아이디를 입력해주세요');
            return;
        }
        // 아이디 중복체크
        //fetch('/checkId/'+id)
        fetch(`/checkId/${id}`)
        // json형식의 문자열을 javascript object 로 변환
        .then(res => res.json())
        .then(data => {
            console.log(data);
            if(data.res == '1'){
                // 아이디가 중복됨
                msgbox.innerHTML = '중복된 아이디 입니다.';
                msgbox.classList.add('msgbox')
            } else {
                // 사용가능한 아이디
                // 서버에 전달 될수 있도록 처리
                checkId.value='1';
                msgbox.innerHTML = '사용가능한 아이디 입니다.';
                // css제거
                msgbox.classList.remove('msgbox')
            }
        })
    });
})
