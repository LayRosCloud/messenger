package ru.betrayal.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.betrayal.messenger.entities.MessageEntity;
import ru.betrayal.messenger.entities.ParticipantEntity;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByParticipant(ParticipantEntity participant);
}
