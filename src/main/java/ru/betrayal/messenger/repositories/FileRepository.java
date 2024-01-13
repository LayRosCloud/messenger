package ru.betrayal.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.betrayal.messenger.entities.FileEntity;
import ru.betrayal.messenger.entities.MessageEntity;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByMessage(MessageEntity message);
}
