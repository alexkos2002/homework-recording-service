package soa.labs.kosiuk.homeworkrecordingservice.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentCreateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.student.StudentUpdateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;
import soa.labs.kosiuk.homeworkrecordingservice.facade.StudentFacade;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;
import soa.labs.kosiuk.homeworkrecordingservice.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DefaultStudentFacade implements StudentFacade {

    private StudentService studentService;

    private StudentCreateConverter studentCreateConverter;

    private StudentReadConverter studentReadConverter;

    private StudentUpdateConverter studentUpdateConverter;

    @Autowired
    public DefaultStudentFacade(StudentService studentService, StudentCreateConverter studentCreateConverter,
                                StudentReadConverter studentReadConverter, StudentUpdateConverter studentUpdateConverter) {
        this.studentService = studentService;
        this.studentCreateConverter = studentCreateConverter;
        this.studentReadConverter = studentReadConverter;
        this.studentUpdateConverter = studentUpdateConverter;
    }

    @Override
    public HttpResponseWrapper<StudentReadData> createStudent(StudentCreateData studentCreateData) {
        Student student = studentCreateConverter.convertDtoToEntity(studentCreateData);
        Optional<Student> createdStudentOptional = studentService.createStudent(student);
        if (createdStudentOptional.isPresent()) {
            return HttpResponseWrapper.<StudentReadData>builder()
                    .httpStatus(HttpStatus.CREATED)
                    .data(studentReadConverter.convertEntityToDto(createdStudentOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<StudentReadData>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<List<StudentReadData>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentReadData> studentsReadDtos = students.stream()
                .map(studentReadConverter::convertEntityToDto)
                .collect(Collectors.toList());
        return HttpResponseWrapper.<List<StudentReadData>>builder()
                .httpStatus(HttpStatus.OK)
                .data(studentsReadDtos)
                .build();
    }

    @Override
    public HttpResponseWrapper<StudentReadData> getStudentById(UUID id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isPresent()) {
            return HttpResponseWrapper.<StudentReadData>builder()
                    .httpStatus(HttpStatus.OK)
                    .data(studentReadConverter.convertEntityToDto(studentOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<StudentReadData>builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<StudentReadData> updateStudent(StudentUpdateData studentUpdateData) {
        Student student = studentUpdateConverter.convertDtoToEntity(studentUpdateData);
        Optional<Student> updatedStudentOptional = studentService.updateStudent(student);
        if (updatedStudentOptional.isPresent()) {
            return HttpResponseWrapper.<StudentReadData>builder()
                    .httpStatus(HttpStatus.OK)
                    .data(studentReadConverter.convertEntityToDto(updatedStudentOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<StudentReadData>builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<UUID> deleteStudentById(UUID id) {
        Optional<Student> deletedStudentOptional = studentService.deleteStudentById(id);
        if (deletedStudentOptional.isEmpty()) {
            return HttpResponseWrapper.<UUID>builder()
                    .httpStatus(HttpStatus.NO_CONTENT)
                    .data(id)
                    .build();
        }
        return HttpResponseWrapper.<UUID>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(id)
                .build();
    }
}
