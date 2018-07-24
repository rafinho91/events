package pl.sda.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.events.model.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByUserEntityId(Long userId);
    List<CommentEntity> findByEventEntityId(Long eventId);
}
