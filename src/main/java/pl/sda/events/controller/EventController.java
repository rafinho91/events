package pl.sda.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.events.model.CommentEntity;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.CommentService;
import pl.sda.events.service.EventService;
import pl.sda.events.service.ParticipationService;
import pl.sda.events.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    private EventService eventService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public EventController(EventService eventService, UserService userService, CommentService commentService) {
        this.eventService = eventService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        List<EventEntity> allEvents = eventService.findAllEvents();
        modelAndView.addObject("events", allEvents);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreateEventPage(ModelAndView modelAndView) {
        EventEntity eventEntity = new EventEntity();
        modelAndView.addObject("eventEntity", eventEntity);
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createEvent(ModelAndView modelAndView, @Valid EventEntity eventEntity){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(email);
        eventEntity.setUserEntity(byEmail);
        eventService.saveEvent(eventEntity);
        modelAndView.addObject("confirmationMessage",
                "Your: " + eventEntity.getName() + " has been created successfully");
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @RequestMapping(value = "/eventInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showEventInfoPage(ModelAndView modelAndView, @PathVariable String id, CommentEntity commentNew) {
        Optional<EventEntity> eventById = eventService.findEventById(Long.valueOf(id));
        EventEntity event = new EventEntity();
        UserEntity organiser = new UserEntity();
        if (eventById.isPresent()){
            event = eventById.get();
            organiser = event.getUserEntity();
        }
        List<CommentEntity> allComments = eventService.findAllComments(Long.valueOf(id));
        modelAndView.addObject("eventEntity", event);
        modelAndView.addObject("userEntity", organiser);
        modelAndView.addObject("comments", allComments);
        modelAndView.addObject("commentNew", commentNew);
        modelAndView.setViewName("eventInfo");
        return modelAndView;
    }

    @RequestMapping(value = "eventInfo/{id}/addComment", method = RequestMethod.POST)
    public String addComment(ModelAndView modelAndView, CommentEntity commentAdd, @PathVariable String id){
        EventEntity eventEntity = eventService.findEventById(Long.valueOf(id)).get();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(email);
        CommentEntity comment = CommentEntity.builder()
                .userEntity(byEmail).eventEntity(eventEntity).content(commentAdd.getContent()).build();
        commentService.addComment(comment);
        return "redirect:/eventInfo/" + eventEntity.getId();
    }




}
