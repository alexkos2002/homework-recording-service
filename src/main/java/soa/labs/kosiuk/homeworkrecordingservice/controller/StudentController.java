package soa.labs.kosiuk.homeworkrecordingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;
import soa.labs.kosiuk.homeworkrecordingservice.facade.StudentFacade;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentFacade studentFacade;

    @Autowired
    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @PostMapping
    public ResponseEntity<StudentReadData> createStudent(@RequestBody StudentCreateData studentCreateData) {
        HttpResponseWrapper<StudentReadData> createdStudentHttpWrapper = studentFacade.createStudent(studentCreateData);
        return ResponseEntity.status(createdStudentHttpWrapper.getHttpStatus())
                .body(createdStudentHttpWrapper.getData());
    }

    @GetMapping
    public ResponseEntity<List<StudentReadData>> getAllStudents() {
        HttpResponseWrapper<List<StudentReadData>> studentsHttpWrapper = studentFacade.getAllStudents();
        return ResponseEntity.status(studentsHttpWrapper.getHttpStatus())
                .body(studentsHttpWrapper.getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentReadData> getStudentById(@PathVariable("id") UUID id) {
        HttpResponseWrapper<StudentReadData> studentHttpWrapper = studentFacade.getStudentById(id);
        return ResponseEntity.status(studentHttpWrapper.getHttpStatus())
                .body(studentHttpWrapper.getData());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentReadData> updateStudent(@RequestBody StudentUpdateData studentUpdateData, @PathVariable("id") UUID id) {
        HttpResponseWrapper<StudentReadData> updatedStudentHttpWrapper = studentFacade.updateStudent(studentUpdateData);
        return ResponseEntity.status(updatedStudentHttpWrapper.getHttpStatus())
                .body(updatedStudentHttpWrapper.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteStudent(@PathVariable("id") UUID id) {
        HttpResponseWrapper<UUID> deletedStudentHttpWrapper = studentFacade.deleteStudentById(id);
        return ResponseEntity.status(deletedStudentHttpWrapper.getHttpStatus())
                .body(deletedStudentHttpWrapper.getData());
    }

}
