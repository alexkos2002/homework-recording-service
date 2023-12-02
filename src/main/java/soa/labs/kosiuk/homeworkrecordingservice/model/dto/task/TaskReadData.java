package soa.labs.kosiuk.homeworkrecordingservice.model.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectReadData;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class TaskReadData {

    private UUID id;

    private StudentReadData student;

    private SubjectReadData subject;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;

}
