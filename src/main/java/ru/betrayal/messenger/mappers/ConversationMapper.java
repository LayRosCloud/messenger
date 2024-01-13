package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import ru.betrayal.messenger.dtos.conversations.ConversationResponseDto;
import ru.betrayal.messenger.entities.ConversationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    List<ConversationResponseDto> toListDto(List<ConversationEntity> conversations);
    ConversationResponseDto toDto(ConversationEntity conversation);
}
