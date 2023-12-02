package soa.labs.kosiuk.homeworkrecordingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;

import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {

}
