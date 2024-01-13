package ru.betrayal.messenger.dtos.messages;

import lombok.Data;

@Data
public class UpdateMessageDto {
    private Long id;
    private Long participantId;
    private String message;
}
