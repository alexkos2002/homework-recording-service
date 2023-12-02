package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.task;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskCreateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.composite.TaskId;

@Component
public class DefaultTaskCreateConverter implements TaskCreateConverter {

    @Override
    public Task convertDtoToEntity(TaskCreateData data) {

        return Task.builder()
                .studentId(data.getStudentId())
                .subjectId(data.getSubjectId())
                .title(data.getTitle())
                .content(data.getContent())
                .deadline(data.getDeadline())
                .build();
    }

    @Override
    public TaskCreateData convertEntityToDto(Task task) {
        return null;
    }
}
