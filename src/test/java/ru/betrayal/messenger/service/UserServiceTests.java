package ru.betrayal.messenger.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.betrayal.messenger.dtos.users.UserResponseDto;
import ru.betrayal.messenger.entities.UserEntity;
import ru.betrayal.messenger.mappers.UserMapper;
import ru.betrayal.messenger.repositories.UserRepository;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.impl.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository repository;

    @Spy
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void findAll_happyPath() {
        UserEntity user = new UserEntity(1L, "vogistv@gmail.com", "12345678", null, null, new Date());
        UserEntity user1 = new UserEntity(2L, "vogistv1@gmail.com", "1234567", null, null, new Date());
        when(repository.findAll()).thenReturn(List.of(user, user1));

        List<UserResponseDto> users = service.findAll();

        Assertions.assertEquals(2, users.size());
    }

    @Test
    void findById_whenNotFound() {
        when(repository.findById(3L)).thenReturn(Optional.empty());


        Assertions.assertThrows(NotFoundException.class, ()->
            service.findById(3L)
        );
    }
}
