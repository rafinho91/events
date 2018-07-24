package pl.sda.events.service;

import org.springframework.security.core.userdetails.UserDetails;
import pl.sda.events.model.EventEntity;
import pl.sda.events.model.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getActiveUser(String email);
    UserEntity findByEmail(String email);
    UserEntity findByConfirmationToken(String confirmationToken);
    void saveUser(UserEntity userEntity);
    List<EventEntity> findEventsByUserEntityId(Long userEntityId);

}
