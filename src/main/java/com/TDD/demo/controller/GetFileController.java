package com.TDD.demo.controller;

import com.TDD.demo.repository.model.FileEntity;
import com.TDD.demo.service.FileService;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This works well only for pdf type.
@RestController
@RequestMapping("api/v1")
public class GetFileController {
    @Autowired
    private FileService fileService;
    @GetMapping("/upload/{id}")

    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileEntity fileEntity = fileService.getFileById(id);

        if (fileEntity != null) {
            String mimeType = determineFileType(fileEntity.getData());

            if (!"application/pdf".equals(mimeType)) {
                // Discourage users from uploading other types of files
                return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(mimeType));
            headers.setContentDispositionFormData("attachment", fileEntity.getFileName());

            return new ResponseEntity<>(fileEntity.getData(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //detecting the file type using Apache Tika
    public static String determineFileType(byte[] fileData){
        Tika tika = new Tika();
        try{
            return tika.detect(fileData);
        }catch (Exception e){
            System.out.println("Fell Here?");
            return "Media type not supported";
        }
    }
}