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
    public int insertUpload(List<MultipartFile> uploadFiles, String path) {

        // 파일의 정보를 db에 저장 하기 위해서 시퀀스 값을 조회
        int f_no = mapper.selectSeqUploadFile();
        // uploadFiles에 전달된 파일을 서버에 저장하고 DB에 입력후 f_no를 반환

        // 서버로 전달된 파일 리스트를 돌면서
        // 파일을 저장하고
        // 파일정보를 DB에 저장
        // ===================================================================
        for (int i = 0; i < uploadFiles.size(); i++) {
            // 리스트에 담긴 파일을 꺼내옴
            MultipartFile file = uploadFiles.get(i);
            // Dto 객체에 세팅, 폴더생성 - 원본파일명, 저장할 파일명등 세팅
            UploadDto uploadDto = makeUploadDto(file, path, i, f_no);
            log.info(uploadDto.toString());

            try {
                // 파일을 서버에 저장
                File uploadFile = new File("d:/upload/" + path + File.separator + uploadDto.getSname());
                file.transferTo(uploadFile);
                // 파일정보를 DB 저장 + f_no반환
                mapper.insertUpload(uploadDto);
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return f_no;
        // ===================================================================
    }

    // uploadDto 세팅
    private UploadDto makeUploadDto(MultipartFile file, String path, int idx, int f_no) {
        UploadDto uploadDto = new UploadDto();
        // 파일의 소실을 막기위해 파일의 이름을 변경
        uploadDto.setOname(file.getOriginalFilename());
        uploadDto.setSnameValue(file.getOriginalFilename());
        uploadDto.setPath(path);
        uploadDto.setIdx(idx + 1);
        uploadDto.setFile_type(file.getContentType());
        // 조회된 시퀀스값을 입력하여 파일을 묶어준다
        uploadDto.setF_no(f_no);

        String dir = "d:/upload/" + path + File.separator;
        // 디렉토리가 없으면 디렉토리 생성
        makeDir(dir);

        return uploadDto;
    }

    // 디렉토리가 존재 하지 않으면 디렉토리를 생성
    private void makeDir(String dir) {
        File uploadDir = new File(dir);
        // 경로(폴더-디렉토리)가 존재하지 않으면 디렉토리를 생성
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    public List<UploadDto> selectUploadList() {
        return mapper.selectUploadList();
    }
}
