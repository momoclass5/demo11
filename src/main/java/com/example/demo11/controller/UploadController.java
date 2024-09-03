package com.example.demo11.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo11.service.UploadService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class UploadController {
    @GetMapping("/upload")
    public void getMethodName() {
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
        return "/upload";
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
        return "/upload";
    }

}
