package soa.labs.kosiuk.homeworkrecordingservice.model.entity.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class TaskId implements Serializable {

    private UUID id;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "subject_id")
    private UUID subjectId;

}
