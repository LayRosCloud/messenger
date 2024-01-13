package ru.betrayal.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.betrayal.messenger.entities.ParticipantEntity;
import ru.betrayal.messenger.entities.UserEntity;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    Iterable<ParticipantEntity> findAllByUser(UserEntity user);
}
