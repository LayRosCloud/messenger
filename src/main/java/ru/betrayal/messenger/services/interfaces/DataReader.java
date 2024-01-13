package ru.betrayal.messenger.services.interfaces;

import ru.betrayal.messenger.scripts.NotFoundException;

import java.util.List;

public interface DataReader<TResult, VKey> {
    List<TResult> findAll();
    TResult findById(VKey id) throws NotFoundException;
}
