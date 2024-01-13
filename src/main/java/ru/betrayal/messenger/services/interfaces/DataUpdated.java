package ru.betrayal.messenger.services.interfaces;

import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;

public interface DataUpdated<TDto, VResult> {
    VResult update(TDto dto) throws NotFoundException;
}
