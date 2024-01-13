package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import ru.betrayal.messenger.dtos.messages.MessageResponseDto;
import ru.betrayal.messenger.entities.MessageEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    List<MessageResponseDto> toListDto(List<MessageEntity> messages);
    MessageResponseDto toDto(MessageEntity message);
}
