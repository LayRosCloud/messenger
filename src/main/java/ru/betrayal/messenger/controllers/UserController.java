package ru.betrayal.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.betrayal.messenger.dtos.users.UserCreateRequestDto;
import ru.betrayal.messenger.dtos.users.UserUpdateRequestDto;
import ru.betrayal.messenger.dtos.users.UserResponseDto;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.UserService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/file")
    public String fin(@RequestPart MultipartFile file, UserCreateRequestDto dto) {
        return file.getResource().getFilename();
    }

    @GetMapping()
    public List<UserResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public UserResponseDto findById(@PathVariable Long id) throws NotFoundException {
        return service.findById(id);
    }

    @PostMapping
    public UserResponseDto create(@RequestBody UserCreateRequestDto item) throws BadRequestException {
        return service.create(item);
    }

    @PutMapping
    public UserResponseDto update(@RequestPart UserUpdateRequestDto item, @RequestPart(required = false) MultipartFile image) throws NotFoundException, BadRequestException {
        return service.update(item, image);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        service.remove(id);
    }
}
