package ru.betrayal.messenger.services.interfaces;

import ru.betrayal.messenger.scripts.BadRequestException;

public interface DataCreator<TDto, VResult> {
    VResult create(TDto dto) throws BadRequestException;
}
