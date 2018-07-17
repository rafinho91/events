package pl.sda.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.CommentService;
import pl.sda.events.service.EventService;
import pl.sda.events.service.ParticipationService;
import pl.sda.events.service.UserService;

@Controller
public class EventController {

    private UserService userService;
    private EventService eventService;
    private ParticipationService participationService;
    private CommentService commentService;

    @Autowired
    public EventController(UserService userService, EventService eventService,
                           ParticipationService participationService, CommentService commentService) {
        this.userService = userService;
        this.eventService = eventService;
        this.participationService = participationService;
        this.commentService = commentService;
    }

//    @GetMapping(value = "/register")
//    public String registerUserForm(Model model){
//        model.addAttribute("user", new UserEntity());
//        return "register";
//    }
//
//    @PostMapping(value = "/register")
//    public ResponseEntity<String> registerUser(@ModelAttribute UserEntity user, Model model ){
//        userService.addToDb(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/showAll")
//    public String showAll(){
//        return userService.showAll().toString();
//    }




}
