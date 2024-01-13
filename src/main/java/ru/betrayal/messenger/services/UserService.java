package ru.betrayal.messenger.services;

import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.users.UserCreateRequestDto;
import ru.betrayal.messenger.dtos.users.UserResponseDto;
import ru.betrayal.messenger.dtos.users.UserUpdateRequestDto;
import ru.betrayal.messenger.entities.UserEntity;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;

public interface UserService extends
        DataReader<UserResponseDto, Long>,
        DataCreator<UserCreateRequestDto, UserResponseDto>,
        DataRemover<Long> {
    UserEntity findByEmail(String email) throws NotFoundException;
    UserResponseDto create(UserCreateRequestDto userEntity) throws BadRequestException;
    UserResponseDto update(UserUpdateRequestDto userEntity, MultipartFile file) throws NotFoundException, BadRequestException;
    void remove(Long id) throws NotFoundException;
}
