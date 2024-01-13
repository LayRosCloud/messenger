package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.users.UserCreateRequestDto;
import ru.betrayal.messenger.dtos.users.UserResponseDto;
import ru.betrayal.messenger.dtos.users.UserUpdateRequestDto;
import ru.betrayal.messenger.entities.UserEntity;
import ru.betrayal.messenger.mappers.UserMapper;
import ru.betrayal.messenger.repositories.UserRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.FileUploader;
import ru.betrayal.messenger.services.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ru.betrayal.messenger.scripts.ThrowableErrors.throwBadRequestException;
import static ru.betrayal.messenger.scripts.ThrowableErrors.throwNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final FileUploader uploader;

    @Override
    public List<UserResponseDto> findAll() {
        List<UserEntity> users = repository.findAll();
        return mapper.toListDto(users);
    }

    @Override
    public UserResponseDto findById(Long id) throws NotFoundException {
        UserEntity entity = find(id);

        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public UserResponseDto create(UserCreateRequestDto user) throws BadRequestException {
        if(existEmailToDatabase(user.getEmail())){
            throw throwBadRequestException("Error! User with email '%s' exists", user.getEmail());
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setDate(new Date());

        UserEntity entity = repository.save(userEntity);

        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public UserResponseDto update(UserUpdateRequestDto user, MultipartFile image) throws NotFoundException, BadRequestException {
        UserEntity entity = find(user.getId());

        if(!user.getEmail().equals(entity.getEmail()) && existEmailToDatabase(user.getEmail())){
            throw throwBadRequestException("Error! User with email %s exists", user.getEmail());
        }
        String path = null;
        if(image != null){
            path = uploader.upload(image);
        }
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setStatus(user.getStatus());
        entity.setImage(path);

        UserEntity result = repository.save(entity);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public void remove(Long id) throws NotFoundException {
        UserEntity entity = find(id);
        repository.delete(entity);
    }

    @Override
    public UserEntity findByEmail(String email) throws NotFoundException {
        UserEntity entity = repository.findByEmail(email).orElseThrow(()->
                throwNotFoundException("User with email '%s' not found or incorrect password", email)
        );

        return entity;
    }

    private UserEntity find(Long id) throws NotFoundException {
        UserEntity user = repository.findById(id).orElseThrow(() ->
                throwNotFoundException(String.valueOf(id))
        );
        return user;
    }

    private boolean existEmailToDatabase(String email){
        Optional<UserEntity> optionalUser = repository.findByEmail(email);

        return optionalUser.isPresent();
    }
}
