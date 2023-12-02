package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.student;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;

@Component
public class DefaultStudentReadConverter implements StudentReadConverter {
    @Override
    public Student convertDtoToEntity(StudentReadData data) {
        return null;
    }

    @Override
    public StudentReadData convertEntityToDto(Student student) {
        return StudentReadData.builder()
                .id(student.getId())
                .name(student.getName())
                .password(student.getPassword())
                .email(student.getEmail())
                .build();
    }
}
