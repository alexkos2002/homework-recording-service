package soa.labs.kosiuk.homeworkrecordingservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;
import soa.labs.kosiuk.homeworkrecordingservice.repository.StudentRepository;
import soa.labs.kosiuk.homeworkrecordingservice.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultStudentService implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> createStudent(Student student) {
        return Optional.ofNullable(studentRepository.save(student));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        Optional<Student> studentToUpdateOptional = studentRepository.findById(student.getId());
        if (studentToUpdateOptional.isPresent()) {
            return Optional.ofNullable(studentRepository.save(student));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Student> deleteStudentById(UUID id) {
        studentRepository.deleteById(id);
        return studentRepository.findById(id);
    }

}
