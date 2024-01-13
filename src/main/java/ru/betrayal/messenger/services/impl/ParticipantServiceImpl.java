package ru.betrayal.messenger.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.betrayal.messenger.dtos.participantes.CreateParticipantDto;
import ru.betrayal.messenger.dtos.participantes.ParticipantResponseDto;
import ru.betrayal.messenger.dtos.participantes.UpdateParticipantDto;
import ru.betrayal.messenger.entities.ConversationEntity;
import ru.betrayal.messenger.entities.ParticipantEntity;
import ru.betrayal.messenger.entities.RoleEntity;
import ru.betrayal.messenger.entities.UserEntity;
import ru.betrayal.messenger.mappers.ParticipantMapper;
import ru.betrayal.messenger.repositories.ConversationRepository;
import ru.betrayal.messenger.repositories.ParticipantRepository;
import ru.betrayal.messenger.repositories.RoleRepository;
import ru.betrayal.messenger.repositories.UserRepository;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;
import ru.betrayal.messenger.scripts.ThrowableErrors;
import ru.betrayal.messenger.services.ParticipantService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository repository;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConversationRepository conversationRepository;

    private final ParticipantMapper mapper;

    @Override
    @Transactional
    public ParticipantResponseDto create(CreateParticipantDto dto) throws BadRequestException, NotFoundException {
        ParticipantEntity participant = new ParticipantEntity();
        searchAttrs(dto.getUserId(), dto.getRoleId(), dto.getConversationId(), participant);

        participant.setCreated(new Date());

        ParticipantEntity result = repository.save(participant);
        return mapper.toDto(result);
    }

    @Override
    public List<ParticipantResponseDto> findAll() {
        List<ParticipantEntity> result = repository.findAll();


        return mapper.toListDto(result);
    }

    @Override
    public ParticipantResponseDto findById(Long id) throws NotFoundException {
        ParticipantEntity result = find(id);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public void remove(Long id) throws NotFoundException {
        ParticipantEntity entity = find(id);
        repository.delete(entity);
    }

    @Override
    @Transactional
    public ParticipantResponseDto update(UpdateParticipantDto dto) throws NotFoundException {
        ParticipantEntity entity = find(dto.getId());

        searchAttrs(dto.getUserId(), dto.getRoleId(), dto.getConversationId(), entity);

        ParticipantEntity result = repository.save(entity);

        return mapper.toDto(result);
    }

    private ParticipantEntity find(Long id){
        return repository.findById(id).orElseThrow(()->
                ThrowableErrors.throwNotFoundException(String.valueOf(id))
        );
    }

    private void searchAttrs(Long userId, Short roleId, Long conversationId, ParticipantEntity entity){
        UserEntity user = userRepository.findById(userId).orElseThrow(()->
                ThrowableErrors.throwNotFoundException("Error! User not founded")
        );

        RoleEntity role = roleRepository.findById(roleId).orElseThrow(()->
                ThrowableErrors.throwNotFoundException("Error! Role not founded")
        );

        ConversationEntity conversation = conversationRepository.findById(conversationId).orElseThrow(()->
                ThrowableErrors.throwNotFoundException("Error! Conversation not founded")
        );

        entity.setConversation(conversation);
        entity.setRole(role);
        entity.setUser(user);
    }

}
