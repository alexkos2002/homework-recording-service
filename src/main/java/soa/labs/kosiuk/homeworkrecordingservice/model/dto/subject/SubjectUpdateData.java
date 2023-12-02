package soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class SubjectUpdateData {

    private UUID id;

    private String name;

}
