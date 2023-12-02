package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.student;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentUpdateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;

@Component
public class DefaultStudentUpdateConverter implements StudentUpdateConverter {
    @Override
    public Student convertDtoToEntity(StudentUpdateData data) {
        return Student.builder()
                .id(data.getId())
                .name(data.getName())
                .password(data.getPassword())
                .email(data.getEmail())
                .build();
    }

    @Override
    public StudentUpdateData convertEntityToDto(Student entity) {
        return null;
    }
}
