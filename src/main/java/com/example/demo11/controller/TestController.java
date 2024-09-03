package com.example.demo11.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

    @GetMapping("/download")
    // ResponseEntity : HTTP응답객체
    public ResponseEntity<byte[]> getMethodName(@RequestParam(name = "fileName") String fileName,
            @RequestParam(name = "img", required = false) String img) throws IOException {
        log.info("download file : " + fileName);
        File file = new File("d:\\upload\\" + fileName);

        // 파일의 타입및 암호화된패턴등은 헤더에 지정
        HttpHeaders headers = new HttpHeaders();

        try {

            // 파일이 존재하면 파일을 읽어들여 브라우저에 전달
            if (file.exists()) {
                String mimeType = MediaType.APPLICATION_OCTET_STREAM.toString();
                // Mime 타입을 다운받을수 있는 타입으로 지정
                if ("Y".equals(img)) {
                    mimeType = URLConnection.guessContentTypeFromName(file.getName());
                }
                headers.add("Content-Type", mimeType); // 다운로드시 저장되는 이름을 지정 (한글이
                // 깨지는것을 막기위해 인코딩 처리가 필요합니다 )
                // 컨텐츠에 대한 추가 설명 및 파일 이름
                headers.add("Content-Disposition", "attachment; filename=\""
                        + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

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

    @GetMapping("/test")
    public ResponseEntity<String> getMethodName1() {
        ResponseEntity.status(HttpStatus.CREATED) // 상태 코드 설정
                .headers(new HttpHeaders()) // 헤더 설정
                .body("responseBody");
        // return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("<b>body</b>");
    }

}
