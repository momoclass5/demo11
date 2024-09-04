package com.example.demo11.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo11.dto.UploadDto;
import com.example.demo11.service.UploadService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UploadController {
    @GetMapping("/upload")
    public void getMethodName(Model model) {
        List<UploadDto> list = service.selectUploadList();
        model.addAttribute("list", list);
    }

    /**
     * 첨부파일 저장
     * 
     * RequestPart어노테이션을 이용하여 전달된 파일을
     * MultipartFile 타입 변수에 저장
     * 
     * 전달된 파일이 있다면
     * multiPartFile의 transferTo 메서드를 이용하여 서버에 파일을 저장
     * 경로가 없는경우, 파일이 없는경우 예외가 발생 할수 있다
     * 
     * @param entity
     * @return
     */
    @PostMapping("/uploadAction")
    public String postMethodName(
            @RequestPart(name = "file", required = false) MultipartFile file, Model model) {
        log.info("file info start ===========================");
        log.info(file.getOriginalFilename()); // 파일 이름
        log.info(file.getName()); // name속성의 값
        log.info(file.getSize() + ""); // 파일 사이즈
        log.info(file.getContentType()); // 파일 형식
        log.info("file info end ===========================");

        // 사용자로 부터 전달된 파일을 d:/upload/ 경로에 저장
        File uploadFile = new File("d:/upload/" + file.getOriginalFilename());
        try {
            // 파일을 저장
            // 만약 같은 이름의 파일이 존재 한다면 덮어쓰기가 됨
            // 반환타입 없음
            // 파일이 저장 되거나 예외가 발생
            if (file.isEmpty()) {
                log.info("파일이 없습니다.");
                model.addAttribute("msg", "선택된 파일이 없습니다.");
                return "upload";
            }
            file.transferTo(uploadFile);
        } catch (IllegalStateException e) {
            log.info("파일 저장 실패 : IllegalStateException");
            e.printStackTrace();
        } catch (IOException e) {
            // 경로가 존재하지 않는경우
            // 전달된 파일이 없는경우
            log.info("파일 저장 실패 : IOException");
            e.printStackTrace();
        }
        return "redirect:/upload";
    }

    @Autowired
    UploadService service;

    /**
     * multiple 옵션을 사용하여 여려개의 파일이 전달될 경우
     * 
     * @param uploadFiles
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/uploadActionMultiple")
    public String postMethodName(
            @RequestPart(name = "uploadFiles", required = false) List<MultipartFile> uploadFiles)
            throws IllegalStateException, IOException {

        service.insertUpload(uploadFiles, "multiple");
        return "redirect:/upload";
    }

    // /download?fileName=/multiple/bootstrap-logo (1).svg&oname=테스트.svg
    // sname, oname
    @GetMapping("/download")
    public ResponseEntity<byte[]> getMethodName(@RequestParam(name = "fileName") String fileName,
            @RequestParam(name = "oname") String oname) throws IOException {
        // fileName = /path/ + sname
        // oname = oname
        log.info("download file : " + fileName);

        File file = new File("d:\\upload\\" + fileName);

        try {
            // 응답헤더 설정 (파일의 타입및 암호화된패턴등은 헤더에 지정)
            HttpHeaders headers = new HttpHeaders();

            // 파일이 존재하면 파일을 읽어들여 브라우저에 전달
            if (file.exists()) {

                // Mime 타입을 다운받을수 있는 타입으로 지정
                // String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                // if (mimeType == null)
                String mimeType = MediaType.APPLICATION_OCTET_STREAM.toString();
                // Content-Type, MediaType
                // 파일 또는 바이트 집합의 성격과 형식을 나타내는 값
                // MediaType 객체에 정의 되어 있음
                // 웹브라우저는 mediaType(Content-Type)을 확인하고 전달받은 문서의 종류를 인식
                headers.add("Content-Type", mimeType); // 다운로드시 저장되는 이름을 지정 (한글이
                // 깨지는것을 막기위해 인코딩 처리가 필요합니다 )
                // 컨텐츠에 대한 추가 설명 및 파일 이름
                headers.add("Content-Disposition", "attachment; filename=\""
                        + new String(oname.getBytes("UTF-8"), "ISO-8859-1") + "\"");

                // ResponseEntity<>(본문데이터(body), 응답헤더객체, Http상태코드)
                return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
            } else {
                // 파일이 없는경우 상태코드 404 전달
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (UnsupportedEncodingException e) {
            // 오류 발생시 상태코드 500 전달
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /*
     * ResponseEntity
     * HTTP응답을 처리할때 사용되는 객체
     * 
     * 상태코드 : HttpStatus에 정의된 상태코드
     * 헤더정보 : 응답헤더 - HttpHeaders를 생성하여 속성을 추가
     * 본문 : body영역 - 실제 데이터 또는 본문 메세지
     * 
     */

    @GetMapping("/test")
    public ResponseEntity<String> test() {

        // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // return new ResponseEntity<>("responseEntity", HttpStatus.OK);
        return ResponseEntity
                .status(HttpStatus.OK) // 상태코드
                .headers(new HttpHeaders()) // 응답헤더
                .body("body"); // 본문(바디)

    }

}
