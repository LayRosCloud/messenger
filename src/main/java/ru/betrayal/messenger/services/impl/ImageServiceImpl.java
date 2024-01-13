package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.images.CreateImageDto;
import ru.betrayal.messenger.dtos.images.ImageResponseDto;
import ru.betrayal.messenger.dtos.images.UpdateImageDto;
import ru.betrayal.messenger.entities.ImageEntity;
import ru.betrayal.messenger.entities.MessageEntity;
import ru.betrayal.messenger.mappers.ImageMapper;
import ru.betrayal.messenger.repositories.ImageRepository;
import ru.betrayal.messenger.repositories.MessageRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.scripts.ThrowableErrors;
import ru.betrayal.messenger.services.ImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;
    private final MessageRepository messageRepository;
    private final ImageMapper mapper;

    @Override
    @Transactional
    public ImageResponseDto create(CreateImageDto dto, MultipartFile file) throws BadRequestException {
        ImageEntity image = new ImageEntity();
        String path = ""; //TODO: make upload

        image.setUrl(path);
        searchAttrs(dto.getMessageId(), image);

        ImageEntity result = repository.save(image);

        return mapper.toDto(result);
    }

    @Override
    public List<ImageResponseDto> findAll() {
        List<ImageEntity> list = repository.findAll();
        return mapper.toListDto(list);
    }

    @Override
    public ImageResponseDto findById(Long id) throws NotFoundException {
        ImageEntity entity = find(id);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public void remove(Long id) throws NotFoundException {
        ImageEntity image = find(id);
        repository.delete(image);
    }

    @Override
    @Transactional
    public ImageResponseDto update(UpdateImageDto dto, MultipartFile file) throws NotFoundException {
        ImageEntity image = find(dto.getId());
        String path = ""; //TODO: make upload

        image.setUrl(path);
        searchAttrs(dto.getMessageId(), image);

        ImageEntity result = repository.save(image);

        return mapper.toDto(result);
    }

    private ImageEntity find(Long id) throws NotFoundException{
        ImageEntity image = repository.findById(id).orElseThrow(()->
                ThrowableErrors.throwNotFoundException(String.valueOf(id))
        );
        return image;
    }

    private void searchAttrs(Long messageId, ImageEntity image) throws NotFoundException{
        MessageEntity message = messageRepository.findById(messageId).orElseThrow(()->
                ThrowableErrors.throwNotFoundException(String.valueOf(messageId))
        );
        image.setMessage(message);
    }

}
