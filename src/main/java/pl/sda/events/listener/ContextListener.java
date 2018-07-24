package pl.sda.events.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.events.model.Access;
import pl.sda.events.model.CommentEntity;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;
import pl.sda.events.repository.CommentRepository;
import pl.sda.events.repository.EventRepository;
import pl.sda.events.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class ContextListener{

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    CommentRepository commentRepository;

    @PostConstruct
    public void contextInitialized() {

        UserEntity rafal = UserEntity.builder()
                .firstName("Rafal")
                .lastName("Drgas")
                .enabled(true)
                .email("rafal91@gmail.com")
                .password(passwordEncoder.encode("rafal"))
                .role("ROLE_USER")
                .confirmationToken(UUID.randomUUID().toString())
                .build();
        userRepository.save(rafal);
        UserEntity robert = UserEntity.builder()
                .firstName("Robert")
                .lastName("Drgas")
                .enabled(true)
                .email("robert87@gmail.com")
                .password(passwordEncoder.encode("robert"))
                .role("ROLE_USER")
                .confirmationToken(UUID.randomUUID().toString())
                .build();
        userRepository.save(robert);

        EventEntity event = EventEntity.builder()
                .access(Access.PRIVATE)
                .date(LocalDate.of(2018,2,15))
                .time(LocalTime.of(21,0))
                .address("POZNAN")
                .userEntity(rafal)
                .name("Rafal PARTY")
                .build();
        eventRepository.save(event);

        EventEntity event2 = EventEntity.builder()
                .access(Access.PUBLIC)
                .date(LocalDate.of(2018,4,7))
                .time(LocalTime.of(19,0))
                .address("WARSZAWA")
                .userEntity(rafal)
                .name("Rafal PARTY - vol.2")
                .build();
        eventRepository.save(event2);
        EventEntity event3 = EventEntity.builder()
                .access(Access.PUBLIC)
                .date(LocalDate.of(2018,5,17))
                .time(LocalTime.of(20,0))
                .address("KRAKOW")
                .userEntity(robert)
                .name("Robert PARTY")
                .build();
        eventRepository.save(event3);
        EventEntity event4 = EventEntity.builder()
                .access(Access.PRIVATE)
                .date(LocalDate.of(2018,8,17))
                .time(LocalTime.of(21,0))
                .address("POZNAN")
                .userEntity(robert)
                .name("Robert PARTY - vol.2")
                .build();
        eventRepository.save(event4);

        CommentEntity comment = CommentEntity.builder()
                .eventEntity(event)
                .userEntity(rafal)
                .content("This is an AMAZING party!!! Can't wait for it :D")
                .build();
        commentRepository.save(comment);
        CommentEntity comment2 = CommentEntity.builder()
                .eventEntity(event)
                .userEntity(robert)
                .content("It will be spectacular - the food will be delicious")
                .build();
        commentRepository.save(comment2);
        CommentEntity comment3 = CommentEntity.builder()
                .eventEntity(event)
                .userEntity(rafal)
                .content("Thanks Robert, it's really nice to hear that! :)")
                .build();
        commentRepository.save(comment3);

    }

}
