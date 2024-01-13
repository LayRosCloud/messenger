package ru.betrayal.messenger.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;

public interface FileUploader {
    String upload(MultipartFile file) throws BadRequestException;
    ClassPathResource download(String filename) throws NotFoundException;
}
