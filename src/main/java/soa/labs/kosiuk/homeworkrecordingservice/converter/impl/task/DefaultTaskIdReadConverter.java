package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.task;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskIdReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskIdReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.composite.TaskId;

@Component
public class DefaultTaskIdReadConverter implements TaskIdReadConverter {
    @Override
    public TaskId convertDtoToEntity(TaskIdReadData data) {
        return null;
    }

    @Override
    public TaskIdReadData convertEntityToDto(TaskId taskId) {
        return TaskIdReadData.builder()
                .id(taskId.getId())
                .studentId(taskId.getStudentId())
                .subjectId(taskId.getSubjectId())
                .build();
    }
}
