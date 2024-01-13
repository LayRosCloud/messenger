package ru.betrayal.messenger.mappers;

import org.mapstruct.Mapper;
import ru.betrayal.messenger.dtos.roles.CreateRoleDto;
import ru.betrayal.messenger.dtos.roles.RoleResponseDto;
import ru.betrayal.messenger.entities.RoleEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    List<RoleResponseDto> toListDto(List<RoleEntity> roles);
    RoleResponseDto toDto(RoleEntity role);
    RoleEntity toEntity(CreateRoleDto dto);
}
