package soa.labs.kosiuk.homeworkrecordingservice.facade;

import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskIdReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;

import java.util.List;
import java.util.UUID;

public interface TaskFacade {

    HttpResponseWrapper<TaskReadData> createTask(TaskCreateData taskCreateData);

    HttpResponseWrapper<List<TaskReadData>> getAllTasks();

    HttpResponseWrapper<TaskReadData> getTaskById(UUID studentId, UUID subjectId, UUID id);

    HttpResponseWrapper<List<TaskReadData>> getTasksByStudents(List<UUID> studentsIds);

    HttpResponseWrapper<List<TaskReadData>> getTasksBySubjects(List<UUID> subjectsIds);

    HttpResponseWrapper<TaskReadData> updateTask(TaskUpdateData taskUpdateData);

    HttpResponseWrapper<TaskIdReadData> deleteTaskById(UUID studentId, UUID subjectId, UUID id);
}
