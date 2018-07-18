package pl.sda.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegisterController {

    private EmailService emailService;

    @Autowired
    public RegisterController(EmailService emailService){
        this.emailService = emailService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserEntity userEntity) {
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UserEntity userEntity,
                                                BindingResult bindingResult, HttpServletRequest request) {
        return emailService.processRegistrationForm(modelAndView, userEntity, bindingResult, request);
        }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView,
                                             @RequestParam String token){
        return emailService.showConfirmationPage(modelAndView, token);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
                                                @RequestParam Map requestParams, RedirectAttributes redir){
        return emailService.processConfirmationForm(modelAndView,bindingResult,requestParams,redir);
    }

}
