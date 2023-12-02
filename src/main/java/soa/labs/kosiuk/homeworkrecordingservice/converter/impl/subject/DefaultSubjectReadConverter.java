package soa.labs.kosiuk.homeworkrecordingservice.converter.impl.subject;

import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.subject.SubjectReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.subject.SubjectReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;

@Component
public class DefaultSubjectReadConverter implements SubjectReadConverter {

    @Override
    public Subject convertDtoToEntity(SubjectReadData data) {
        return null;
    }

    @Override
    public SubjectReadData convertEntityToDto(Subject subject) {
        return SubjectReadData.builder()
                .id(subject.getId())
                .name(subject.getName())
                .build();
    }

}
