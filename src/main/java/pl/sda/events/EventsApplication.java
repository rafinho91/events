package pl.sda.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.sda.events.model.UserEntity;
import pl.sda.events.repository.EventRepository;

@SpringBootApplication
public class EventsApplication {

    private static final Logger log = LoggerFactory.getLogger(EventsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(EventRepository repository){
//        return (args -> {
//            repository.save(UserEntity.builder()
//            .email("rafal@gmail.com")
//            .login("rafal")
//            .password("password")
//            .username("rafinho")
//            .build());
//
//            repository.save(UserEntity.builder()
//                    .email("adam@gmail.com")
//                    .login("adam")
//                    .password("password")
//                    .username("adinho")
//                    .build());
//
//            log.info("Users found by findAll: ");
//            log.info("-------------------------");
//
//            for (UserEntity userEntity : repository.findAll()){
//                log.info(userEntity.toString());
//            }
//        });
//    }

}
