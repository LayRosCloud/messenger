package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.betrayal.messenger.dtos.roles.CreateRoleDto;
import ru.betrayal.messenger.dtos.roles.RoleResponseDto;
import ru.betrayal.messenger.dtos.roles.UpdateRoleDto;
import ru.betrayal.messenger.entities.RoleEntity;
import ru.betrayal.messenger.mappers.RoleMapper;
import ru.betrayal.messenger.repositories.RoleRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.services.RoleService;

import java.util.List;
import java.util.Optional;

import static ru.betrayal.messenger.scripts.ThrowableErrors.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    @Transactional
    public RoleResponseDto create(CreateRoleDto createRoleDto) throws BadRequestException {
        if(existName(createRoleDto.getName())){
            throw throwBadRequestException("role with name exists");
        }

        RoleEntity role = mapper.toEntity(createRoleDto);

        RoleEntity result = repository.save(role);
        return mapper.toDto(result);
    }

    @Override
    public List<RoleResponseDto> findAll() {
        List<RoleEntity> roles = repository.findAll();
        return mapper.toListDto(roles);
    }

    @Override
    public RoleResponseDto findById(Short id) throws NotFoundException {
        RoleEntity role = find(id);
        return mapper.toDto(role);
    }

    @Override
    @Transactional
    public void remove(Short id) throws NotFoundException {
        RoleEntity role = find(id);
        repository.delete(role);
    }

    @Override
    @Transactional
    public RoleResponseDto update(UpdateRoleDto updateRoleDto) throws NotFoundException {
        RoleEntity role = find(updateRoleDto.getId());

        if(!role.getName().equals(updateRoleDto.getName()) && existName(updateRoleDto.getName())){
            throw throwBadRequestException("Exception! Name %s is exists", updateRoleDto.getName());
        }

        role.setName(updateRoleDto.getName());

        RoleEntity result = repository.save(role);

        return mapper.toDto(result);
    }

    private RoleEntity find(Short id){
        RoleEntity role = repository.findById(id).orElseThrow(() ->
                throwNotFoundException(String.valueOf(id))
        );
        return role;
    }

    private Boolean existName(String name){
        Optional<RoleEntity> role = repository.findByName(name);

        return role.isPresent();
    }
}
