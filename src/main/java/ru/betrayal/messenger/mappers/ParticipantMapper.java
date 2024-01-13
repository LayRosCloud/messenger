package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import ru.betrayal.messenger.dtos.participantes.ParticipantResponseDto;
import ru.betrayal.messenger.entities.ParticipantEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantResponseDto toDto(ParticipantEntity participant);
    List<ParticipantResponseDto> toListDto(List<ParticipantEntity> participants);
}
