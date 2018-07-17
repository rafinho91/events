package pl.sda.events.controller;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.EmailService;
import pl.sda.events.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder,
                              UserService userService, EmailService emailService){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserEntity userEntity) {
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView,
                                                @Valid UserEntity userEntity,
                                                BindingResult bindingResult,
                                                HttpServletRequest request) {
        UserEntity userExists = userService.findByEmail(userEntity.getEmail());
        System.out.println(userExists);
        if (userExists != null){
            modelAndView.addObject("alreadyRegisteredMessage",
                    "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()){
            modelAndView.setViewName("register");
        }else{
            //Disable user until they click on cofirmation link in email
            userEntity.setConfirmationToken(UUID.randomUUID().toString());

            userService.saveUser(userEntity);

            String appUrl = request.getScheme() + "//" + request.getServerName() + ":" + request.getServerPort();

            SimpleMailMessage registrationMail = new SimpleMailMessage();
            registrationMail.setTo(userEntity.getEmail());
            registrationMail.setSubject("Registration Confirmation");
            registrationMail.setText("To confirm your account, please click the link below:\n"
                    + appUrl + "/confirm?token=" + userEntity.getConfirmationToken());
            registrationMail.setFrom("noreply@events.com");

            emailService.sendEmail(registrationMail);

            modelAndView.addObject("confirmationMessage",
                    "A confirmation e-mail has been sent to " + userEntity.getEmail());
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView,
                                             @RequestParam String token){
        UserEntity userEntity = userService.findByConfirmationToken(token);

        if (userEntity == null){
            modelAndView.addObject("invalidToken",
                    "This is an invalid confirmation link.");
        }else {
            modelAndView.addObject("confirmationToken",
                    userEntity.getConfirmationToken());
        }
        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView,
                                                BindingResult bindingResult,
                                                @RequestParam Map requestParams,
                                                RedirectAttributes redir){
        modelAndView.setViewName("confirm");
        Zxcvbn passwordCheck = new Zxcvbn();
        Strength strength = passwordCheck.measure(requestParams.get("password").toString());
        if (strength.getScore() < 3){
            bindingResult.reject("password");
            redir.addFlashAttribute("errorMessage", "Your password is too weak. Choose a stronger one");

            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        UserEntity userEntity = userService.findByConfirmationToken(requestParams.get("token").toString());
        userEntity.setPassword(passwordEncoder.encode(requestParams.get("password").toString()));
        userEntity.setEnabled(true);
        userService.saveUser(userEntity);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }

}
