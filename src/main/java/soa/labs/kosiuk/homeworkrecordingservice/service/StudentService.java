package soa.labs.kosiuk.homeworkrecordingservice.service;

import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {

    Optional<Student> createStudent(Student student);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(UUID id);

    Optional<Student> updateStudent(Student student);

    Optional<Student> deleteStudentById(UUID id);

}
