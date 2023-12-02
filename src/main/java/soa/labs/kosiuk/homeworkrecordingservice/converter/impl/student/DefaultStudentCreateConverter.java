package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.student;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentCreateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;

@Component
public class DefaultStudentCreateConverter implements StudentCreateConverter {

    @Override
    public Student convertDtoToEntity(StudentCreateData studentCreateData) {
        return Student.builder()
                .name(studentCreateData.getName())
                .password(studentCreateData.getPassword())
                .email(studentCreateData.getEmail())
                .build();
    }

    @Override
    public StudentCreateData convertEntityToDto(Student entity) {
        return null;
    }

}
