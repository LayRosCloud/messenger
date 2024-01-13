package ru.betrayal.messenger.services;

import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.images.CreateImageDto;
import ru.betrayal.messenger.dtos.images.ImageResponseDto;
import ru.betrayal.messenger.dtos.images.UpdateImageDto;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;
import ru.betrayal.messenger.services.interfaces.DataUpdated;

public interface ImageService extends
        DataReader<ImageResponseDto, Long>,
        DataRemover<Long> {
    ImageResponseDto create(CreateImageDto dto, MultipartFile file) throws BadRequestException;

    ImageResponseDto update(UpdateImageDto dto, MultipartFile file) throws NotFoundException;
}
