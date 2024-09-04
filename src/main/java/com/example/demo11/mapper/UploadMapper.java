package com.example.demo11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo11.dto.UploadDto;

@Mapper
public interface UploadMapper {
    public int insertUpload(UploadDto uploadDto);

    // 목록을 조회
    public List<UploadDto> selectUploadList();

    // f_no에 해당하는 정보를 조회
    public List<UploadDto> selectUpload(int f_no);

    public int selectSeqUploadFile();
}
