package soa.labs.kosiuk.homeworkrecordingservice.facade;

import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;

import java.util.List;
import java.util.UUID;

public interface SubjectFacade {

    HttpResponseWrapper<SubjectReadData> createSubject(SubjectCreateData subjectCreateData);

    HttpResponseWrapper<List<SubjectReadData>> getAllSubjects();


    HttpResponseWrapper<SubjectReadData> getSubjectById(UUID id);

    HttpResponseWrapper<SubjectReadData> updateSubject(SubjectUpdateData subjectUpdateData);

    HttpResponseWrapper<UUID> deleteSubjectById(UUID id);

}
