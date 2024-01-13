package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.messages.CreateMessageDto;
import ru.betrayal.messenger.dtos.messages.MessageResponseDto;
import ru.betrayal.messenger.dtos.messages.UpdateMessageDto;
import ru.betrayal.messenger.entities.*;
import ru.betrayal.messenger.mappers.MessageMapper;
import ru.betrayal.messenger.repositories.MessageRepository;
import ru.betrayal.messenger.repositories.ParticipantRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.scripts.ThrowableErrors;
import ru.betrayal.messenger.services.MessageService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final ParticipantRepository participantrepository;
    private final MessageMapper mapper;

    @Override
    @Transactional
    public MessageResponseDto create(CreateMessageDto dto, MultipartFile audio, MultipartFile video) throws BadRequestException {
        MessageEntity message = new MessageEntity();

        if(video != null){
            //TODO: save video
        }

        if(audio != null){
            //TODO: save audio
        }

        message.setMessage(dto.getMessage());
        message.setDate(new Date());
        searchAttrs(dto.getParticipantId(), message);

        MessageEntity result = repository.save(message);

        return mapper.toDto(result);
    }

    @Override
    public List<MessageResponseDto> findAll() {
        List<MessageEntity> list = repository.findAll();

        return mapper.toListDto(list);
    }

    @Override
    public MessageResponseDto findById(Long id) throws NotFoundException {
        MessageEntity item = find(id);
        return mapper.toDto(item);
    }

    @Override
    @Transactional
    public void remove(Long id) throws NotFoundException {
        MessageEntity item = find(id);
        repository.delete(item);
    }

    @Override
    @Transactional
    public MessageResponseDto update(UpdateMessageDto dto, MultipartFile audio, MultipartFile video) throws NotFoundException {
        MessageEntity message = find(dto.getId());

        if(video != null){
            //TODO: save video
        }

        if(audio != null){
            //TODO: save audio
        }

        message.setMessage(dto.getMessage());
        message.setDate(new Date());
        searchAttrs(dto.getParticipantId(), message);

        MessageEntity result = repository.save(message);
        return mapper.toDto(result);
    }

    private MessageEntity find(Long id){
        MessageEntity item = repository.findById(id).orElseThrow(()->
                ThrowableErrors.throwNotFoundException(String.valueOf(id))
        );

        return item;
    }

    private void searchAttrs(Long participantId, MessageEntity entity){

        ParticipantEntity participant = participantrepository.findById(participantId).orElseThrow(()->
                ThrowableErrors.throwNotFoundException("Error! Participant not founded")
        );

        entity.setParticipant(participant);
    }
}
