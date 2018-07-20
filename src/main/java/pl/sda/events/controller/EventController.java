package pl.sda.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.CommentService;
import pl.sda.events.service.EventService;
import pl.sda.events.service.ParticipationService;
import pl.sda.events.service.UserService;

import javax.validation.Valid;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreateEventPage(ModelAndView modelAndView, EventEntity eventEntity) {
        modelAndView.addObject("eventEntity", eventEntity);
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createEvent(ModelAndView modelAndView, @Valid EventEntity eventEntity){
        eventService.saveEvent(eventEntity);
        modelAndView.addObject("confirmationMessage",
                "Your: " + eventEntity.getName() + " has been created successfully");
        modelAndView.setViewName("create");
        return modelAndView;
    }




}
