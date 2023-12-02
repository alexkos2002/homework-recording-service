package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.task;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskUpdateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;

@Component
public class DefaultTaskUpdateConverter implements TaskUpdateConverter {
    @Override
    public Task convertDtoToEntity(TaskUpdateData data) {
        return Task.builder()
                .id(data.getId())
                .studentId(data.getStudentId())
                .subjectId(data.getSubjectId())
                .title(data.getTitle())
                .content(data.getContent())
                .deadline(data.getDeadline())
                .build();
    }

    @Override
    public TaskUpdateData convertEntityToDto(Task entity) {
        return null;
    }
}
