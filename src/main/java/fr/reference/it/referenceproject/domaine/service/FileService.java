package fr.reference.it.referenceproject.domaine.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

@Service
public class FileService {
    private static final String DIR_PATH = "C:/angular/";

    public void uploadFile(MultipartFile pMultipartFile) throws IOException {
        Path filePath = Paths.get(DIR_PATH + pMultipartFile.getOriginalFilename());
        Files.copy(pMultipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
