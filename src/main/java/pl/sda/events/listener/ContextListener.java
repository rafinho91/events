package pl.sda.events.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.events.model.Access;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;
import pl.sda.events.repository.EventRepository;
import pl.sda.events.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ContextListener{

//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    EventRepository eventRepository;
//
//    @PostConstruct
//    public void contextInitialized() {
//
//        UserEntity rafal = UserEntity.builder()
//                .firstName("Rafal")
//                .lastName("Drgas")
//                .enabled(true)
//                .email("rafinho91@gmail.com")
////                .password("rafal")
//                .password(passwordEncoder.encode("rafal"))
//                .role("USER_ROLE")
//                .build();
//        userRepository.save(rafal);
//
////        EventEntity event = EventEntity.builder()
////                .access(Access.PRIVATE)
////                .date(LocalDate.of(2018,2,15))
////                .time(LocalTime.of(21,0))
////                .address("POZNAN")
////                .userEntity(rafal)
////                .name("Rafal PARTY")
////                .build();
////        eventRepository.save(event);



//    }

}
