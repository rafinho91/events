package pl.sda.events.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.UserService;

import javax.annotation.PostConstruct;

@Service
public class ContextListener{

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @PostConstruct
    public void contextInitialized() {

        UserEntity rafal = UserEntity.builder()
                .firstName("Rafal")
                .lastName("Drgas")
                .enabled(true)
                .email("rafinho91@gmail.com")
                .password(passwordEncoder.encode("rafal"))
                .build();
        userService.saveUser(rafal);


//        BankAccount bankAccount = BankAccount.builder()
//                .accountNumber("12345678901234567890123456")
//                .availableFunds(32000)
//                .balance(29030)
//                .currency("PLN")
//                .build();
//        BankAccountRepository bankRepo = new BankAccountRepository();
//        bankRepo.create(bankAccount);
//
//        User build = User.builder()
//                .email("test@gmail.com")
//                .password("password")
//                .name("Rafal")
//                .lastName("Drgas")
//                .bankAccount(bankAccount)
//                .build();
//
//        UserRepository repo = new UserRepository();
//        repo.create(build);



    }

}
