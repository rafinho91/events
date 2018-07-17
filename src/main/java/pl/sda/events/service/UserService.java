package pl.sda.events.service;

import pl.sda.events.model.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findByEmail(String email);
    UserEntity findByConfirmationToken(String confirmationToken);
    void saveUser(UserEntity userEntity);

}
