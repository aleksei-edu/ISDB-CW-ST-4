package com.pokeshop.pokemonshop.service;

import com.pokeshop.pokemonshop.model.FileData;
import com.pokeshop.pokemonshop.repository.StorageRepository;
import com.pokeshop.pokemonshop.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Transactional
    public String uploadFile(MultipartFile file) throws IOException {
        FileData fileData = storageRepository.save(FileData.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(FileUtils.compressFile(file.getBytes()))
                .build());
        if(fileData != null) {
            return "File uploaded successfully: " + fileData.getFileName();
        }
        return null;
    }
    @Transactional

    public FileData dowloadFile(String filename){
        Optional<FileData> dbFileData = storageRepository.findByFileName(filename);
        byte[] file = FileUtils.decompressFile(dbFileData.get().getData());
        return new FileData(dbFileData.get().getId(), dbFileData.get().getFileName(), dbFileData.get().getFileType(), file);
    }

    public String deleteFile(String filename){
        Optional<FileData> dbFileData = storageRepository.findByFileName(filename);
        storageRepository.delete(dbFileData.get());
        return "File deleted successfully: " + filename;
    }
}
