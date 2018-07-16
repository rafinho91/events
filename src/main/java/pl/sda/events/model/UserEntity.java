package pl.sda.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String login;
    String password;

    @Column(unique = true)
    String email;

    String username;

//    @OneToMany(mappedBy = "userEntity")
//    Set<EventEntity> eventEntitySet;


}
