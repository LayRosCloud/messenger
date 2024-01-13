package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.files.CreateFileDto;
import ru.betrayal.messenger.dtos.files.FileResponseDto;
import ru.betrayal.messenger.dtos.files.UpdateFileDto;
import ru.betrayal.messenger.entities.FileEntity;
import ru.betrayal.messenger.entities.MessageEntity;
import ru.betrayal.messenger.mappers.FileMapper;
import ru.betrayal.messenger.repositories.FileRepository;
import ru.betrayal.messenger.repositories.MessageRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.scripts.ThrowableErrors;
import ru.betrayal.messenger.services.FileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository repository;
    private final MessageRepository messageRepository;
    private final FileMapper mapper;

    @Override
    @Transactional
    public FileResponseDto create(CreateFileDto dto, MultipartFile file) throws BadRequestException {
        FileEntity fileEntity = new FileEntity();

        String path = ""; //TODO: file upload

        searchAttrs(dto.getMessageId(), fileEntity);
        fileEntity.setUrl(path);

        FileEntity result = repository.save(fileEntity);

        return mapper.toDto(result);
    }

    @Override
    public List<FileResponseDto> findAll() {
        List<FileEntity> files = repository.findAll();
        return mapper.toListDto(files);
    }

    @Override
    public FileResponseDto findById(Long id) throws NotFoundException {
        FileEntity file = find(id);
        return mapper.toDto(file);
    }

    @Override
    @Transactional
    public void remove(Long id) throws NotFoundException {
        FileEntity file = find(id);
        repository.delete(file);
    }

    @Override
    @Transactional
    public FileResponseDto update(UpdateFileDto dto, MultipartFile file) throws NotFoundException {
        FileEntity fileEntity = find(dto.getId());

        String path = ""; //TODO: file upload

        searchAttrs(dto.getMessageId(), fileEntity);
        fileEntity.setUrl(path);

        FileEntity result = repository.save(fileEntity);

        return mapper.toDto(result);
    }

    private FileEntity find(Long id){
        FileEntity file = repository.findById(id).orElseThrow(()->
                ThrowableErrors.throwNotFoundException(String.valueOf(id))
        );
        return file;
    }

    private void searchAttrs(Long messageId, FileEntity entity){
        MessageEntity message = messageRepository.findById(messageId).orElseThrow(()->
                    ThrowableErrors.throwNotFoundException(String.valueOf(messageId))
                );
        entity.setMessage(message);
    }
}
