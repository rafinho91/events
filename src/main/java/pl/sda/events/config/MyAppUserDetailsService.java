package pl.sda.events.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.UserService;

import java.util.Arrays;
@Service
public class MyAppUserDetailsService implements UserDetailsService{
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email){
        UserEntity activeUser = userService.getActiveUser(email);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole());
        UserDetails userDetails = (UserDetails)new User(activeUser.getEmail(),
                        activeUser.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
