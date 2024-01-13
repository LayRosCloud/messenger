package ru.betrayal.messenger.services;

import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.files.CreateFileDto;
import ru.betrayal.messenger.dtos.files.FileResponseDto;
import ru.betrayal.messenger.dtos.files.UpdateFileDto;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;
import ru.betrayal.messenger.services.interfaces.DataUpdated;

public interface FileService extends
        DataReader<FileResponseDto, Long>,
        DataRemover<Long> {
    FileResponseDto create(CreateFileDto dto, MultipartFile file) throws BadRequestException;

    FileResponseDto update(UpdateFileDto dto, MultipartFile file) throws NotFoundException;
}
