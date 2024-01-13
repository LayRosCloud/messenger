package ru.betrayal.messenger.dtos.images;

import lombok.Data;

@Data
public class ImageResponseDto {
    private Long id;
    private Long messageId;
    private String url;
}
