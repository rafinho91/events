package pl.sda.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.events.model.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
