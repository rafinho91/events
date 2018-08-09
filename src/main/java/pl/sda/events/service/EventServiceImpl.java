package pl.sda.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.events.model.CommentEntity;
import pl.sda.events.model.EventEntity;
import pl.sda.events.repository.CommentRepository;
import pl.sda.events.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;
    private CommentRepository commentRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void saveEvent(EventEntity eventEntity) {
        eventRepository.save(eventEntity);
    }

    @Override
    public Optional<EventEntity> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<CommentEntity> findAllComments(Long eventId) {
        return commentRepository.findByEventEntityId(eventId);
    }

    @Override
    public List<EventEntity> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<EventEntity> findByLocation(String location) {
        return eventRepository.findByAddress(location);
    }
}
