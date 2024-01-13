package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import ru.betrayal.messenger.dtos.files.FileResponseDto;
import ru.betrayal.messenger.entities.FileEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    List<FileResponseDto> toListDto(List<FileEntity> files);
    FileResponseDto toDto(FileEntity file);
}
