package pl.sda.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    EventEntity eventEntity;

    @ManyToOne
    @JoinColumn
    UserEntity userEntity;

    boolean acceptedByAdmin;

    boolean acceptedByParticipant;

}
