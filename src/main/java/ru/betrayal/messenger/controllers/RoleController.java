package ru.betrayal.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.betrayal.messenger.dtos.roles.CreateRoleDto;
import ru.betrayal.messenger.dtos.roles.RoleResponseDto;
import ru.betrayal.messenger.dtos.roles.UpdateRoleDto;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.RoleService;

import java.util.List;

@RestController
@RequestMapping(value = "roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public List<RoleResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public RoleResponseDto findById(@PathVariable Short id) throws NotFoundException {
        return service.findById(id);
    }

    @PostMapping()
    public RoleResponseDto create(@RequestBody CreateRoleDto dto) throws BadRequestException {
        return service.create(dto);
    }

    @PutMapping()
    public RoleResponseDto create(@RequestBody UpdateRoleDto dto) throws BadRequestException, NotFoundException {
        return service.update(dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Short id) throws NotFoundException {
        service.remove(id);
    }
}
