package ru.betrayal.messenger.services.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.scripts.ThrowableErrors;
import ru.betrayal.messenger.services.FileUploader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUploadImpl implements FileUploader {
    @Override
    public String upload(MultipartFile file) throws BadRequestException {
        String[] divided = Objects.requireNonNull(file.getResource().getFilename()).split("\\.");
        String extension = divided[divided.length - 1];
        String name = UUID.randomUUID() + "." + extension;

        try(FileOutputStream stream = new FileOutputStream("src/main/resources/static/" + name)) {
            stream.write(file.getBytes());
            return name;
        }catch (IOException exception){
            exception.printStackTrace();
            throw ThrowableErrors.throwBadRequestException(
                    "Файл имел неверный формат!\n" +
                    "Дополнительная информация: " + exception.getMessage()
            );
        }
    }

    @Override
    public ClassPathResource download(String filename) throws NotFoundException {
        ClassPathResource resource = new ClassPathResource("static/" + filename);
        if(!resource.exists()){
            throw ThrowableErrors.throwNotFoundException("Exception! File is not founded");
        }
        return resource;
    }
}
