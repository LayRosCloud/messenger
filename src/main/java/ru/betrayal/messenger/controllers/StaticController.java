package ru.betrayal.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.FileUploader;

import java.io.IOException;

@RestController
@RequestMapping(value = "static")
@RequiredArgsConstructor
public class StaticController {

    private final FileUploader fileUpload;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws NotFoundException, IOException {
        ClassPathResource resource = fileUpload.download(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+filename)
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(resource.contentLength())
                .body(resource);
    }

}
