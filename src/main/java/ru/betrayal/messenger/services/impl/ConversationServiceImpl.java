package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.conversations.ConversationResponseDto;
import ru.betrayal.messenger.dtos.conversations.CreateConversationDto;
import ru.betrayal.messenger.dtos.conversations.UpdateConversationDto;
import ru.betrayal.messenger.entities.ConversationEntity;
import ru.betrayal.messenger.mappers.ConversationMapper;
import ru.betrayal.messenger.repositories.ConversationRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.scripts.ThrowableErrors;
import ru.betrayal.messenger.services.ConversationService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository repository;
    private final ConversationMapper mapper;

    @Override
    @Transactional
    public ConversationResponseDto create(CreateConversationDto dto, MultipartFile image) throws BadRequestException {
        ConversationEntity entity = new ConversationEntity();
        if(image != null){
            //Todo: Image uploading
        }

        entity.setCreated(new Date());
        entity.setName(dto.getName());

        ConversationEntity result = repository.save(entity);

        return mapper.toDto(result);
    }

    @Override
    public List<ConversationResponseDto> findAll() {
        List<ConversationEntity> list = repository.findAll();
        return mapper.toListDto(list);
    }

    @Override
    public ConversationResponseDto findById(Long id) throws NotFoundException {
        ConversationEntity item = find(id);
        return mapper.toDto(item);
    }

    @Override
    @Transactional
    public void remove(Long id) throws NotFoundException {
        ConversationEntity item = find(id);
        repository.delete(item);
    }

    @Override
    @Transactional
    public ConversationResponseDto update(UpdateConversationDto dto, MultipartFile image) throws NotFoundException {
        ConversationEntity entity = find(dto.getId());
        if(image != null){
            //Todo: Image uploading
        }

        entity.setName(dto.getName());

        ConversationEntity result = repository.save(entity);

        return mapper.toDto(result);
    }

    private ConversationEntity find(Long id){
        ConversationEntity conversation = repository.findById(id).orElseThrow(()->
                ThrowableErrors.throwNotFoundException(String.valueOf(id))
        );
        return conversation;
    }
}
