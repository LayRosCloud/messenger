package ru.betrayal.messenger.services.interfaces;

import ru.betrayal.messenger.scripts.NotFoundException;

public interface DataRemover<VKey>{
    void remove(VKey id) throws NotFoundException;
}
