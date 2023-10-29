package com.TDD.demo.controller;

import com.TDD.demo.model.FileUploadDto;
import com.TDD.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.TDD.demo.controller.GetFileController.determineFileType;

//Only pdf files allowed
@RestController
@RequestMapping("api/v1")
public class PostPdfFileController {
    @Autowired
    FileService fileService;
    @PostMapping("/upload/pdf")
    public String handleFileUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()){
            String mimeType = determineFileType(multipartFile.getBytes());

            if (!"application/pdf".equals(mimeType)) {
                return "Only PDF files are allowed";
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
}
