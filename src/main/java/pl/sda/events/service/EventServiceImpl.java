package pl.sda.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.events.model.EventEntity;
import pl.sda.events.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void saveEvent(EventEntity eventEntity) {
        eventRepository.save(eventEntity);
    }
}
