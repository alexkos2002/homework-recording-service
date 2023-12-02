package soa.labs.kosiuk.homeworkrecordingservice.service;

import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    Optional<Task> createTask(Task task);

    List<Task> getAllTasks();

    Optional<Task> getTaskById(UUID studentId, UUID subjectId, UUID id);

    List<Task> getTasksByStudents(List<UUID> studentsIds);

    List<Task> getTasksBySubjects(List<UUID> subjectsIds);

    Optional<Task> updateTask(Task task);

    Optional<Task> deleteTaskById(UUID studentId, UUID subjectId, UUID id);

}
