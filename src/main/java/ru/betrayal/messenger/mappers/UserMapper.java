package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.betrayal.messenger.dtos.users.UserResponseDto;
import ru.betrayal.messenger.entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "dateRegistration", source = "date")
    UserResponseDto toDto(UserEntity user);
    List<UserResponseDto> toListDto(List<UserEntity> users);
}
