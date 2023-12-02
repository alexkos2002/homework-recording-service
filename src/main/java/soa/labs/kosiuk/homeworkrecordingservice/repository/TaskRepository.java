package soa.labs.kosiuk.homeworkrecordingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.composite.TaskId;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, TaskId> {

    @Query(value = "SELECT * FROM task WHERE student_id IN (:studentsIds)", nativeQuery = true)
    List<Task> findAllByStudentsIds(@Param("studentsIds") List<UUID> studentsIds);

    @Query(value = "SELECT * FROM task WHERE subject_id IN (:subjectsIds)", nativeQuery = true)
    List<Task> findAllBySubjectsIds(@Param("subjectsIds") List<UUID> subjectsIds);
}
