package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;

@Component
public class DefaultTaskReadConverter implements TaskReadConverter {

    private StudentReadConverter studentReadConverter;

    private SubjectReadConverter subjectReadConverter;

    @Autowired
    public DefaultTaskReadConverter(StudentReadConverter studentReadConverter, SubjectReadConverter subjectReadConverter) {
        this.studentReadConverter = studentReadConverter;
        this.subjectReadConverter = subjectReadConverter;
    }

    @Override
    public Task convertDtoToEntity(TaskReadData data) {
        return null;
    }

    @Override
    public TaskReadData convertEntityToDto(Task task) {
        return TaskReadData.builder()
                .id(task.getId())
                .title(task.getTitle())
                .content(task.getContent())
                .timestamp(task.getTimestamp())
                .deadline(task.getDeadline())
                .student(studentReadConverter.convertEntityToDto(task.getStudent()))
                .subject(subjectReadConverter.convertEntityToDto(task.getSubject()))
                .build();
    }

}