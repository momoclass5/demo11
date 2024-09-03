package com.example.demo11.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo11.dto.UploadDto;

@SpringBootTest
public class UploadMapperTest {

    @Autowired
    UploadMapper mapper;

    @Test
    void testInsertUpload() {
        // 파일의 정보를 Dto에 저장후 테이블에 입력
        UploadDto upload = new UploadDto();
        upload.setIdx(1);
        upload.setSname("sName");
        upload.setOname("oName");
        upload.setPath("/book/");
        upload.setFile_type("img");

        // null x
        int res = mapper.insertUpload(upload);
        assertEquals(1, res);
    }
}
