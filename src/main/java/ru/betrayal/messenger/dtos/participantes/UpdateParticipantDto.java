package ru.betrayal.messenger.dtos.participantes;

import lombok.Data;

@Data
public class UpdateParticipantDto {
    private Long id;
    private Long userId;
    private Short roleId;
    private Long conversationId;
}
