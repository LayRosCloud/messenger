package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import ru.betrayal.messenger.dtos.images.ImageResponseDto;
import ru.betrayal.messenger.entities.ImageEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    List<ImageResponseDto> toListDto(List<ImageEntity> images);
    ImageResponseDto toDto(ImageEntity image);
}
