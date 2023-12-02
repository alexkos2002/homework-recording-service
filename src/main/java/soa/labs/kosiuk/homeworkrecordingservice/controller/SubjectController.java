package soa.labs.kosiuk.homeworkrecordingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;
import soa.labs.kosiuk.homeworkrecordingservice.facade.SubjectFacade;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private SubjectFacade subjectFacade;

    @Autowired
    public SubjectController(SubjectFacade subjectFacade) {
        this.subjectFacade = subjectFacade;
    }

    @PostMapping
    public ResponseEntity<SubjectReadData> createSubject(@RequestBody SubjectCreateData subjectCreateData) {
        HttpResponseWrapper<SubjectReadData> createdSubjectHttpWrapper = subjectFacade.createSubject(subjectCreateData);
        return ResponseEntity.status(createdSubjectHttpWrapper.getHttpStatus())
                .body(createdSubjectHttpWrapper.getData());
    }

    @GetMapping
    public ResponseEntity<List<SubjectReadData>> getAllSubjects() {
        HttpResponseWrapper<List<SubjectReadData>> subjectsHttpWrapper = subjectFacade.getAllSubjects();
        return ResponseEntity.status(subjectsHttpWrapper.getHttpStatus())
                .body(subjectsHttpWrapper.getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectReadData> getSubjectById(@PathVariable("id") UUID id) {
        HttpResponseWrapper<SubjectReadData> subjectHttpWrapper = subjectFacade.getSubjectById(id);
        return ResponseEntity.status(subjectHttpWrapper.getHttpStatus())
                .body(subjectHttpWrapper.getData());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectReadData> updateSubject(@RequestBody SubjectUpdateData subjectUpdateData,
                                                         @PathVariable("id") UUID id) {
        HttpResponseWrapper<SubjectReadData> updatedSubjectHttpWrapper = subjectFacade.updateSubject(subjectUpdateData);
        return ResponseEntity.status(updatedSubjectHttpWrapper.getHttpStatus())
                .body(updatedSubjectHttpWrapper.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteSubject(@PathVariable("id") UUID id) {
        HttpResponseWrapper<UUID> deletedSubjectHttpWrapper = subjectFacade.deleteSubjectById(id);
        return ResponseEntity.status(deletedSubjectHttpWrapper.getHttpStatus())
                .body(deletedSubjectHttpWrapper.getData());
    }

}
