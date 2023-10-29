package com.TDD.demo.service;

import com.TDD.demo.model.FileUploadDto;
import com.TDD.demo.repository.FileRepository;
import com.TDD.demo.repository.model.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public void saveFile(FileUploadDto fileUploadDto){
        FileEntity fileEntity = new FileEntity();

        fileEntity.setFileName(fileUploadDto.getFileName());
        fileEntity.setData(fileUploadDto.getData());
        fileRepository.save(fileEntity);
    }

    public FileEntity getFileById(Long id) {
        Optional<FileEntity> fileEntity = fileRepository.findById(id);

        if(fileEntity.isPresent()){
            return fileEntity.get();
        }else{
            return null;
        }
    }
}
