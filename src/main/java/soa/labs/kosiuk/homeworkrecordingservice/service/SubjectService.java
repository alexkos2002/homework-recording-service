package soa.labs.kosiuk.homeworkrecordingservice.service;

import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectService {

    Optional<Subject> createSubject(Subject subject);

    List<Subject> getAllSubjects();

    Optional<Subject> getSubjectById(UUID id);

    Optional<Subject> updateSubject(Subject subject);

    Optional<Subject> deleteSubjectById(UUID id);

}
