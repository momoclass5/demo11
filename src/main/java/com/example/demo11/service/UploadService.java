package com.example.demo11.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo11.dto.UploadDto;
import com.example.demo11.mapper.UploadMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UploadService {
    @Autowired
    UploadMapper mapper;

    /**
     * 사용자로부터 전달된 파일객체 리스트를 매개변수로 받아서
     * 파일을 저장하고 업로드 테이블에 insert
     * 
     * @param uploadFiles
     */
    public void insertUpload(List<MultipartFile> uploadFiles, String path) {
        // 서버로 전달된 파일 리스트를 돌면서
        // 파일을 저장하고
        // 파일정보를 DB에 저장
        for (int i = 0; i < uploadFiles.size(); i++) {
            // 리스트의 인덱스는 0부터 시작
            MultipartFile file = uploadFiles.get(i);
            // for (MultipartFile file : uploadFiles) {
            UploadDto uploadDto = new UploadDto();
            // 파일의 소실을 막기위해 파일의 이름을 변경
            uploadDto.setOname(file.getOriginalFilename());
            uploadDto.setSnameValue(file.getOriginalFilename());
            uploadDto.setPath(path);
            uploadDto.setIdx(i + 1);
            uploadDto.setFile_type(file.getContentType());
            try {
                log.info(uploadDto.toString());
                File uploadFile = new File("d:/upload/" + path + File.separator + file.getOriginalFilename());
                // 파일저장 - 이름을 변경하지 않으면 중복 발생할수 있음
                file.transferTo(uploadFile);
                // 파일정보를 DB 저장
                mapper.insertUpload(uploadDto);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // MultipartFile의 파일정보를 바탕으로
            // uploadDto를 세팅
            mapper.insertUpload(uploadDto);
        }
    }
}
