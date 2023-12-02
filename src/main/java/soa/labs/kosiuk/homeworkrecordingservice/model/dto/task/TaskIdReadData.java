package soa.labs.kosiuk.homeworkrecordingservice.model.dto.task;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class TaskIdReadData {

    private UUID id;

    private UUID studentId;

    private UUID subjectId;

}
