package com.example.demo11.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UploadDto {
    private int f_no;
    private int idx;
    private String oname;
    private String sname;
    private String path;
    private String file_type;
    private String regdate;

    /**
     * 원본파일이름에 Unique 한 값을 붙여 저장할 파일 이름을 생성
     *
     * @param oName
     */
    public void setSnameValue(String oName) {
        // 마지막으로 찾은 매개변수의 위치를 반환
        int index = oName.lastIndexOf(".");
        String fileName = oName.substring(0, index);
        String fileExtension = oName.substring(index);
        String uuid = UUID.randomUUID().toString();
        sname = fileName + "_" + uuid + fileExtension;
    }
}
