package ru.betrayal.messenger.dtos.participantes;

import lombok.Data;
import ru.betrayal.messenger.entities.ConversationEntity;
import ru.betrayal.messenger.entities.RoleEntity;
import ru.betrayal.messenger.entities.UserEntity;

import java.util.Date;

@Data
public class ParticipantResponseDto {
    private Long id;
    private UserEntity user;
    private RoleEntity role;
    private ConversationEntity conversation;
    private Date created;
}
