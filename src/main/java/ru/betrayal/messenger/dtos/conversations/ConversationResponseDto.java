package ru.betrayal.messenger.dtos.conversations;

import lombok.Data;

import java.util.Date;

@Data
public class ConversationResponseDto {
    private Long id;
    private String name;
    private String image;
    private Date created;
}
