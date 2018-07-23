package pl.sda.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.events.model.UserEntity;
import pl.sda.events.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity getActiveUser(String email) {
        UserEntity activeUserEntity = new UserEntity();
//        List<?> list = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email=:email and u.enabled=true")
//                .setParameter("email", email).getResultList();
//        if(!list.isEmpty()) {
//            activeUserEntity = (UserEntity) list.get(0);
//        }
        Optional<UserEntity> byEmail = Optional.ofNullable(userRepository.findByEmail(email));
        if (byEmail.isPresent() && byEmail.get().isEnabled()){
            activeUserEntity = byEmail.get();
        }
        return activeUserEntity;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
