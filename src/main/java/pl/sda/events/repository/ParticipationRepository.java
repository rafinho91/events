package pl.sda.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.events.model.ParticipationEntity;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Long> {
}
