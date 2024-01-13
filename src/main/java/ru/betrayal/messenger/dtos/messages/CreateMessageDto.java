package ru.betrayal.messenger.dtos.messages;

import lombok.Data;

@Data
public class CreateMessageDto {
    private Long participantId;
    private String message;
}
