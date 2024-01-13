package ru.betrayal.messenger.services;

import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.conversations.ConversationResponseDto;
import ru.betrayal.messenger.dtos.conversations.CreateConversationDto;
import ru.betrayal.messenger.dtos.conversations.UpdateConversationDto;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;
import ru.betrayal.messenger.services.interfaces.DataUpdated;

public interface ConversationService extends
        DataReader<ConversationResponseDto, Long>,
        DataRemover<Long> {
    ConversationResponseDto create(CreateConversationDto dto, MultipartFile image) throws BadRequestException;

    ConversationResponseDto update(UpdateConversationDto dto, MultipartFile image) throws NotFoundException;
}
