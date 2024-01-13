package ru.betrayal.messenger.dtos.files;

import lombok.Data;

@Data
public class FileResponseDto {
    private Long id;
    private Long messageId;
    private String url;
}
