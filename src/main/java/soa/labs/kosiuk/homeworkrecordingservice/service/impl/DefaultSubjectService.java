package soa.labs.kosiuk.homeworkrecordingservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;
import soa.labs.kosiuk.homeworkrecordingservice.repository.SubjectRepository;
import soa.labs.kosiuk.homeworkrecordingservice.service.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultSubjectService implements SubjectService {

    SubjectRepository subjectRepository;

    @Autowired
    public DefaultSubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Optional<Subject> createSubject(Subject subject) {
        return Optional.ofNullable(subjectRepository.save(subject));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getSubjectById(UUID id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Optional<Subject> updateSubject(Subject subject) {
        Optional<Subject> subjectToUpdateOptional = subjectRepository.findById(subject.getId());
        if (subjectToUpdateOptional.isPresent()) {
            return Optional.ofNullable(subjectRepository.save(subject));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Subject> deleteSubjectById(UUID id) {
        subjectRepository.deleteById(id);
        return subjectRepository.findById(id);
    }

}
