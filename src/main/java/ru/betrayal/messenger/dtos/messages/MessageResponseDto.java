package ru.betrayal.messenger.dtos.messages;

import lombok.Data;

@Data
public class MessageResponseDto {
    private Long id;
    private Long participantId;
    private String message;
    private String audio;
    private String video;
    private Boolean hasImages;
    private Boolean hasFiles;

}
