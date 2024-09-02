package com.example.demo11.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<byte[]> getMethodName(@RequestParam(name = "fileName") String fileName) throws IOException {
        log.info("download file : " + fileName);
        File file = new File("d:\\upload\\" + fileName);

        // 파일의 타입및 암호화된패턴등은 헤더에 지정
        HttpHeaders headers = new HttpHeaders();

        try {

            // 파일이 존재하면 파일을 읽어들여 브라우저에 전달
            if (file.exists()) {
                // Mime 타입을 다운받을수 있는 타입으로 지정
                headers.add("contentType", MediaType.APPLICATION_OCTET_STREAM.toString()); // 다운로드시 저장되는 이름을 지정 (한글이
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

}
