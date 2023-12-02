package soa.labs.kosiuk.homeworkrecordingservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.composite.TaskId;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
@Entity
@IdClass(TaskId.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Id
    @Column(name = "student_id")
    private UUID studentId;

    @Id
    @Column(name = "subject_id")
    private UUID subjectId;

    private String title;

    private String content;

    private LocalDateTime timestamp;

    private LocalDateTime deadline;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @MapsId("studentId")
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @MapsId("subjectId")
    private Subject subject;

}
