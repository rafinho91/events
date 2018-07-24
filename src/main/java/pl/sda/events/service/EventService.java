package pl.sda.events.service;

import org.springframework.web.servlet.ModelAndView;
import pl.sda.events.model.CommentEntity;
import pl.sda.events.model.EventEntity;
import pl.sda.events.repository.EventRepository;

import java.util.List;
import java.util.Optional;

public interface EventService {

    void saveEvent(EventEntity eventEntity);
    Optional<EventEntity> findEventById(Long id);
    List<CommentEntity> findAllComments(Long eventId);
    List<EventEntity> findAllEvents();

}
