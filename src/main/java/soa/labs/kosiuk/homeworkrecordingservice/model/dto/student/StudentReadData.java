package soa.labs.kosiuk.homeworkrecordingservice.model.dto.student;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class StudentReadData {

    private UUID id;

    private String name;

    private String password;

    private String email;

}
