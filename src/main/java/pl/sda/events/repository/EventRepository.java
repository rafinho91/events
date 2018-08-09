package pl.sda.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.events.model.EventEntity;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long>{

    List<EventEntity> findByUserEntityId(Long userEntityId);
    List<EventEntity> findByAddress(String address);

}
