package com.example.demo11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo11.dto.UploadDto;
import com.example.demo11.mapper.UploadMapper;

@Service
public class UploadService {
    @Autowired
    UploadMapper mapper;

    public void insertUpload(List<MultipartFile> uploadFiles) {
        for (MultipartFile file : uploadFiles) {
            UploadDto uploadDto = new UploadDto();
            // 파일저장
            // 파일의 소실을 막기위해 파일의 이름을 변경

            // MultipartFile의 파일정보를 바탕으로
            // uploadDto를 세팅
            mapper.insertUpload(uploadDto);
        }
    }
}
