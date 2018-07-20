package pl.sda.events.service;

import org.springframework.web.servlet.ModelAndView;
import pl.sda.events.model.EventEntity;

public interface EventService {

    void saveEvent(EventEntity eventEntity);

}
