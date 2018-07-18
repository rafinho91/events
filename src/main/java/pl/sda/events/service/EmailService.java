package pl.sda.events.service;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.events.model.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@Service
public class EmailService {

    private JavaMailSender mailSender;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public EmailService(JavaMailSender mailSender, PasswordEncoder passwordEncoder, UserService userService){
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Async
    public void sendEmail(SimpleMailMessage email){
        mailSender.send(email);
    }

    public ModelAndView processRegistrationForm(ModelAndView modelAndView,
                                                UserEntity userEntity,
                                                BindingResult bindingResult,
                                                HttpServletRequest request) {
        UserEntity userExists = userService.findByEmail(userEntity.getEmail());
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
            SimpleMailMessage registrationMail = createActivationMailMessage(userEntity, request);
            sendEmail(registrationMail);
            modelAndView.addObject("confirmationMessage",
                    "A confirmation e-mail has been sent to " + userEntity.getEmail());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }

    private SimpleMailMessage createActivationMailMessage(UserEntity userEntity, HttpServletRequest request) {
        String appUrl = request.getScheme() + "//" + request.getServerName() + ":" + request.getServerPort();
        SimpleMailMessage registrationMail = new SimpleMailMessage();
        registrationMail.setTo(userEntity.getEmail());
        registrationMail.setSubject("Registration Confirmation");
        registrationMail.setText("To confirm your account, please click the link below:\n"
                + appUrl + "/confirm?token=" + userEntity.getConfirmationToken());
        registrationMail.setFrom("noreply@events.com");
        return registrationMail;
    }

    public ModelAndView showConfirmationPage(ModelAndView modelAndView, String token){
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

    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
                                                Map requestParams, RedirectAttributes redir) {
        modelAndView.setViewName("confirm");
        Zxcvbn passwordCheck = new Zxcvbn();
        Strength strength = passwordCheck.measure(requestParams.get("password").toString());
        if (strength.getScore() < 3) {
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
