package soa.labs.kosiuk.homeworkrecordingservice.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectCreateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectUpdateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;
import soa.labs.kosiuk.homeworkrecordingservice.facade.SubjectFacade;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;
import soa.labs.kosiuk.homeworkrecordingservice.service.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DefaultSubjectFacade implements SubjectFacade {

    private SubjectService subjectService;

    private SubjectReadConverter subjectReadConverter;

    private SubjectCreateConverter subjectCreateConverter;

    private SubjectUpdateConverter subjectUpdateConverter;

    @Autowired
    public DefaultSubjectFacade(SubjectService subjectService, SubjectReadConverter subjectReadConverter,
                                SubjectCreateConverter subjectCreateConverter,
                                SubjectUpdateConverter subjectUpdateConverter) {
        this.subjectService = subjectService;
        this.subjectReadConverter = subjectReadConverter;
        this.subjectCreateConverter = subjectCreateConverter;
        this.subjectUpdateConverter = subjectUpdateConverter;
    }

    @Override
    public HttpResponseWrapper<SubjectReadData> createSubject(SubjectCreateData subjectCreateData) {
        Subject subject = subjectCreateConverter.convertDtoToEntity(subjectCreateData);
        Optional<Subject> createdSubjectOptional = subjectService.createSubject(subject);
        if (createdSubjectOptional.isPresent()) {
            return HttpResponseWrapper.<SubjectReadData>builder()
                    .httpStatus(HttpStatus.CREATED)
                    .data(subjectReadConverter.convertEntityToDto(subject))
                    .build();
        } else {
            return HttpResponseWrapper.<SubjectReadData> builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(subjectReadConverter.convertEntityToDto(subject))
                    .build();
        }
    }

    @Override
    public HttpResponseWrapper<List<SubjectReadData>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<SubjectReadData> subjectReadDtos = subjects.stream()
                .map(subjectReadConverter::convertEntityToDto)
                .collect(Collectors.toList());
        return HttpResponseWrapper.<List<SubjectReadData>> builder()
                .httpStatus(HttpStatus.OK)
                .data(subjectReadDtos)
                .build();
    }

    @Override
    public HttpResponseWrapper<SubjectReadData> getSubjectById(UUID id) {
        Optional<Subject> subjectOptional = subjectService.getSubjectById(id);
        if (subjectOptional.isPresent()) {
            return HttpResponseWrapper.<SubjectReadData> builder()
                    .httpStatus(HttpStatus.OK)
                    .data(subjectReadConverter.convertEntityToDto(subjectOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<SubjectReadData> builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<SubjectReadData> updateSubject(SubjectUpdateData subjectUpdateData) {
        Subject subject = subjectUpdateConverter.convertDtoToEntity(subjectUpdateData);
        Optional<Subject> subjectOptional = subjectService.updateSubject(subject);
        if (subjectOptional.isPresent()) {
            return HttpResponseWrapper.<SubjectReadData> builder()
                    .httpStatus(HttpStatus.OK)
                    .data(subjectReadConverter.convertEntityToDto(subjectOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<SubjectReadData> builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<UUID> deleteSubjectById(UUID id) {
        Optional<Subject> deletedSubjectOptional = subjectService.deleteSubjectById(id);
        if (deletedSubjectOptional.isPresent()) {
            return HttpResponseWrapper.<UUID> builder()
                    .httpStatus(HttpStatus.NO_CONTENT)
                    .data(id)
                    .build();
        }
        return HttpResponseWrapper.<UUID> builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(id)
                .build();
    }

}
