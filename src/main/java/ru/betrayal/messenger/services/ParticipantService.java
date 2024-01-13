package ru.betrayal.messenger.services;

import ru.betrayal.messenger.dtos.participantes.CreateParticipantDto;
import ru.betrayal.messenger.dtos.participantes.ParticipantResponseDto;
import ru.betrayal.messenger.dtos.participantes.UpdateParticipantDto;
import ru.betrayal.messenger.services.interfaces.DataCreator;
import ru.betrayal.messenger.services.interfaces.DataReader;
import ru.betrayal.messenger.services.interfaces.DataRemover;
import ru.betrayal.messenger.services.interfaces.DataUpdated;

public interface ParticipantService extends
        DataReader<ParticipantResponseDto, Long>,
        DataCreator<CreateParticipantDto, ParticipantResponseDto>,
        DataUpdated<UpdateParticipantDto, ParticipantResponseDto>,
        DataRemover<Long> {
}
