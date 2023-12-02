package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.subject;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectUpdateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;

@Component
public class DefaultSubjectUpdateConverter implements SubjectUpdateConverter {
    @Override
    public Subject convertDtoToEntity(SubjectUpdateData data) {
        return Subject.builder()
                .id(data.getId())
                .name(data.getName())
                .build();
    }

    @Override
    public SubjectUpdateData convertEntityToDto(Subject entity) {
        return null;
    }
}
