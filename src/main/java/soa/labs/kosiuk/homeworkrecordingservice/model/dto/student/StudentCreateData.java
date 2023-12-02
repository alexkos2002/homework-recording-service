package soa.labs.kosiuk.homeworkrecordingservice.model.dto.student;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class StudentCreateData {

    private String name;

    private String password;

    private String email;

}
