package com.TDD.demo.model;

import lombok.Data;

@Data
public class FileUploadDto {
    private String fileName;
    private byte[] data;
}
