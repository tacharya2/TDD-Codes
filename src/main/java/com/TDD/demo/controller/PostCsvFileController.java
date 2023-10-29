package com.TDD.demo.controller;

import com.TDD.demo.model.FileUploadDto;
import com.TDD.demo.service.FileService;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// Did not work.
@RestController
@RequestMapping("api/v1")
public class PostCsvFileController {

    @Autowired
    FileService fileService;
    @PostMapping("upload/csv")
    public String csvUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()){

            if (!isCSVFile(multipartFile.getBytes())) {
                return "Only csv files are allowed";
            }
            try{
                FileUploadDto fileUploadDto = new FileUploadDto();
                fileUploadDto.setFileName(multipartFile.getOriginalFilename());
                fileUploadDto.setData(multipartFile.getBytes());

                fileService.saveFile(fileUploadDto);

                return "File Uploaded successfully!";
            }catch (IOException e){
                return "File Upload failed: " + e.getMessage();
            }
        }else{
            return "Please select a file to upload";
        }
    }
    public static boolean isCSVFile(byte[] fileData) {
        Tika tika = new Tika();
        String mimeType = tika.detect(fileData);

        return "text/csv".equals(mimeType);
    }
}