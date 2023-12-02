package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.subject;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectCreateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;

@Component
public class DefaultSubjectCreateConverter implements SubjectCreateConverter {
    @Override
    public Subject convertDtoToEntity(SubjectCreateData data) {
        return Subject.builder()
                .name(data.getName())
                .build();
    }

    @Override
    public SubjectCreateData convertEntityToDto(Subject entity) {
        return null;
    }
}
