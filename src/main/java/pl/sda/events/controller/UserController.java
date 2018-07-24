package pl.sda.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.EventService;
import pl.sda.events.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
    @GetMapping("myAccount")
    public ModelAndView showMyAccount() {
        ModelAndView mav = new ModelAndView();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity byEmail = userService.findByEmail(email);
        mav.addObject("userEntity", byEmail);
        List<EventEntity> eventsByUserEntityId = userService.findEventsByUserEntityId(byEmail.getId());
        mav.addObject("userEvents", eventsByUserEntityId);
        mav.setViewName("myAccount");
        return mav;
    }


//    @GetMapping("/error")
//    public ModelAndView error() {
//        ModelAndView mav = new ModelAndView();
//        String errorMessage= "You are not authorized for the requested data.";
//        mav.addObject("errorMsg", errorMessage);
//        mav.setViewName("403");
//        return mav;
//    }

}
