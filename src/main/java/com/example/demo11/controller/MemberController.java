package com.example.demo11.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    MemberService service;

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String getMethodName() {
        return "/login";
    }

    // 기본적으로 url이 동일하면 오류가 발생
    // 요청방식 다르다면 url이 같아도 됨
    @PostMapping("/login")
    public String getMethodName1() {
        // 로그인 액션 처리
        return "/login";
    }

    @GetMapping("/loginA")
    public String getMethodNamea(HttpServletResponse response) {
        Cookie cookie = new Cookie("userSession", "someSessionId");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(cookie);
        return "/member/login";
    }

    @GetMapping("/getCookie")
    @ResponseBody
    public String getCookie(@RequestParam(name = "cookieName", defaultValue = "userSession") String cookieName,
            @CookieValue(value = "userSession", defaultValue = "not found") String cookieValue) {
        return "Cookie value: " + cookieValue;
    }

    // 로그인 페이지로 이동
    @GetMapping("/register")
    public void register() {
        // return "/member/register";
    }

    // 로그인 처리
    // 아이디 저장하기
    // 체크박스가 체크되어 있으면 아이디를 쿠키에 저장 할수 있도록 처리
    @PostMapping("/loginAction")
    public String postMethodName(MemberDto member, @RequestParam(name = "chkIdSave", required = false) String chkIdSave,
            HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
        // ✨파라메터로 HttpSession 을 받으면 jseSessionId값이 쿠키에 전달됩니다
        log.info("id : " + member.getId());
        log.info("pw : " + member.getPw());
        log.info("chkIdSave : " + chkIdSave);

        Cookie cookie = new Cookie("IdSave", member.getId());
        cookie.setPath("/");
        // 체크박스가 선택되어 있다면 아이디를 쿠키에 저장
        if ("1".equals(chkIdSave)) {
            // 쿠키 생성
            // 로그인을 할때마다 쿠키의 유효기간을 늘려준다!
            cookie.setMaxAge(60 * 60 * 24 * 7);
        } else {
            // 쿠키 제거
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
        // member/loginAction
        log.info("/member/loginAction");

        // 아이디 비밀번호를 수집하고
        // 로그인성공이면 메인페이지로 이동
        // 로그인 성공이면 - 도서목록(/)
        MemberDto loginMember = service.login(member);
        if (loginMember != null) {
            // 세션영역에 데이터 추가
            // 내장객체
            // session : 로그인시 이용, 브라우져에 쿠키에 저장된 세션
            // jsesessionid 라는 이름으로 쿠키가 생성되어지고 요청할때마다 쿠키를 서버에 전달함
            // reqeust : Model 에 데이터를 추가 하게 되면 request 영역에 저장
            session.setAttribute("loginId", loginMember.getId());
            session.setAttribute("loginName", loginMember.getName());

            // 세션의 저장된 값을 반환
            session.getAttribute("loginId");
            // 화면(html)의 경로(주소)를 반환
            // ✨ 서버에서 서버를 호출는 방식
            // 서버에서 서버의 다른 경로를 요청하는 방식
            // 1. forward
            // - 서버에서 처리
            // - 요청 주소가 변경되지 않음
            // 2. redirect
            // - 웹 브라우저로 redirect 요청을 보남
            // - 웹 브라우저에서 다시 경로를 호출
            // - 요청 주소가 변경 됨
            //
            // 요청방식이 달라지면 forward시 오류가 발생
            // /loginAction이 post 방식으로 요청 되었기 때문에 /로 forward 시 오류가 발생
            // return "redirect:/";
            return "redirect:/";
        }
        // 실패이면 로그인페이지로 이동
        // 템플릿 경로에 있는 html문서를 보여줌
        // 로그인 실패이면 - 다시 로그인 페이지로 가서 메세지를 화면에 출력
        model.addAttribute("msg", "아이디 비밀번호를 확인해주세요");
        return "/login";

    }

    @GetMapping("/logoutAction")
    public String getMethodName(HttpSession session) {
        // 세션만료처리 = 로그아웃
        session.invalidate();

        return "redirect:/member/login";
    }

    @PostMapping("/registerAction")
    public String postMethodName(MemberDto member, Model model,
            @RequestPart(name = "file", required = false) MultipartFile file) {
        log.info(file.getOriginalFilename());
        log.info("/registerAction");
        log.info("id : " + member.getId());
        log.info("pw : " + member.getPw());
        log.info("name : " + member.getName());
        int res = service.insertMember(member);
        if (res > 0) {
            // 입력성공 메세지 출력후 로그인 페이지로
            model.addAttribute("msg", "로그인후 이용이 가능합니다.");
            model.addAttribute("url", "/member/login");
        } else {
            // 입력실패 메세지 출력후 뒤로가기
            model.addAttribute("msg", "입력중 예외가 발생 하였습니다. \n관리자에게 문의해주세요.");
        }
        // 메세지를 출력후 원하는 페이지로이동
        // 만약 페이지가 없다면 뒤로가기
        return "/common/msg";
    }

    @GetMapping("/list")
    public String selectList(Model model, SearchDto searchDto) {
        log.info("searchDto : " + searchDto);

        // 데이터베이스에서 사용자 목록을 조회
        Map<String, Object> map = service.selectMemberList(searchDto);
        model.addAttribute("map", map);

        return "/member/list";
    }

}
