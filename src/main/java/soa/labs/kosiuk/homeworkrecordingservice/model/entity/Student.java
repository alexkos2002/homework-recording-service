package soa.labs.kosiuk.homeworkrecordingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String password;

    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Task> tasks;

}
