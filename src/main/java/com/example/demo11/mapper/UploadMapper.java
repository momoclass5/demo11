package com.example.demo11.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo11.dto.UploadDto;

@Mapper
public interface UploadMapper {
    public int insertUpload(UploadDto uploadDto);
}
