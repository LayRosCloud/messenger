package ru.betrayal.messenger.dtos.participantes;

import lombok.Data;

@Data
public class CreateParticipantDto {
    private Long userId;
    private Short roleId;
    private Long conversationId;
}
