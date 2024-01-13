package ru.betrayal.messenger.services;

import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.messages.CreateMessageDto;
import ru.betrayal.messenger.dtos.messages.MessageResponseDto;
import ru.betrayal.messenger.dtos.messages.UpdateMessageDto;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;
import ru.betrayal.messenger.services.interfaces.DataUpdated;

public interface MessageService extends
        DataReader<MessageResponseDto, Long>,
        DataRemover<Long> {
    MessageResponseDto create(CreateMessageDto dto, MultipartFile audio, MultipartFile video) throws BadRequestException;
    MessageResponseDto update(UpdateMessageDto dto, MultipartFile audio, MultipartFile video) throws BadRequestException;
}
