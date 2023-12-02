package soa.labs.kosiuk.homeworkrecordingservice.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskCreateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskIdReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskReadConverter;
import soa.labs.kosiuk.homeworkrecordingservice.converter.task.TaskUpdateConverter;
import soa.labs.kosiuk.homeworkrecordingservice.facade.TaskFacade;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskIdReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;
import soa.labs.kosiuk.homeworkrecordingservice.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DefaultTaskFacade implements TaskFacade {

    private final TaskService taskService;

    private final TaskCreateConverter taskCreateConverter;

    private final TaskReadConverter taskReadConverter;

    private final TaskUpdateConverter taskUpdateConverter;

    private final TaskIdReadConverter taskIdReadConverter;

    @Autowired
    public DefaultTaskFacade(TaskService taskService, TaskCreateConverter taskCreateConverter,
                             TaskReadConverter taskReadConverter, TaskUpdateConverter taskUpdateConverter,
                             TaskIdReadConverter taskIdReadConverter) {
        this.taskService = taskService;
        this.taskCreateConverter = taskCreateConverter;
        this.taskReadConverter = taskReadConverter;
        this.taskUpdateConverter = taskUpdateConverter;
        this.taskIdReadConverter = taskIdReadConverter;
    }

    @Override
    public HttpResponseWrapper<TaskReadData> createTask(TaskCreateData taskCreateData) {
        Task task = taskCreateConverter.convertDtoToEntity(taskCreateData);
        Optional<Task> createdTaskOptional = taskService.createTask(task);
        if (createdTaskOptional.isPresent()) {
            return HttpResponseWrapper.<TaskReadData> builder()
                    .httpStatus(HttpStatus.CREATED)
                    .data(taskReadConverter.convertEntityToDto(task))
                    .build();
        }
        return HttpResponseWrapper.<TaskReadData> builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<List<TaskReadData>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskReadData> tasksReadDtos = tasks.stream()
                .map(taskReadConverter::convertEntityToDto)
                .collect(Collectors.toList());
        return HttpResponseWrapper.<List<TaskReadData>> builder()
                .httpStatus(HttpStatus.OK)
                .data(tasksReadDtos)
                .build();
    }

    @Override
    public HttpResponseWrapper<TaskReadData> getTaskById(UUID studentId, UUID subjectId, UUID id) {
        Optional<Task> taskOptional = taskService.getTaskById(studentId, subjectId, id);
        if (taskOptional.isPresent()) {
            return HttpResponseWrapper.<TaskReadData> builder()
                    .httpStatus(HttpStatus.OK)
                    .data(taskReadConverter.convertEntityToDto(taskOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<TaskReadData> builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<List<TaskReadData>> getTasksByStudents(List<UUID> studentsIds) {
        List<Task> tasks = taskService.getTasksByStudents(studentsIds);
        List<TaskReadData> tasksReadDtos = tasks.stream()
                .map(taskReadConverter::convertEntityToDto)
                .collect(Collectors.toList());
        return HttpResponseWrapper.<List<TaskReadData>> builder()
                .httpStatus(HttpStatus.OK)
                .data(tasksReadDtos)
                .build();
    }

    public HttpResponseWrapper<List<TaskReadData>> getTasksBySubjects(List<UUID> subjectsIds) {
        List<Task> tasks = taskService.getTasksBySubjects(subjectsIds);
        List<TaskReadData> tasksReadDtos = tasks.stream()
                .map(taskReadConverter::convertEntityToDto)
                .collect(Collectors.toList());
        return HttpResponseWrapper.<List<TaskReadData>> builder()
                .httpStatus(HttpStatus.OK)
                .data(tasksReadDtos)
                .build();
    }

    @Override
    public HttpResponseWrapper<TaskReadData> updateTask(TaskUpdateData taskUpdateData) {
        Task task = taskUpdateConverter.convertDtoToEntity(taskUpdateData);
        Optional<Task> updatedTaskOptional = taskService.updateTask(task);
        if (updatedTaskOptional.isPresent()) {
            return HttpResponseWrapper.<TaskReadData> builder()
                    .httpStatus(HttpStatus.OK)
                    .data(taskReadConverter.convertEntityToDto(updatedTaskOptional.get()))
                    .build();
        }
        return HttpResponseWrapper.<TaskReadData> builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(null)
                .build();
    }

    @Override
    public HttpResponseWrapper<TaskIdReadData> deleteTaskById(UUID studentId, UUID subjectId, UUID id) {
        Optional<Task> deletedTaskOptional = taskService.deleteTaskById(studentId, subjectId, id);
        TaskIdReadData taskIdReadData = TaskIdReadData.builder()
                .id(id)
                .studentId(studentId)
                .subjectId(subjectId)
                .build();
        if (deletedTaskOptional.isEmpty()) {
            return HttpResponseWrapper.<TaskIdReadData> builder()
                    .httpStatus(HttpStatus.NO_CONTENT)
                    .data(taskIdReadData)
                    .build();
        }
        return HttpResponseWrapper.<TaskIdReadData> builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .data(taskIdReadData)
                .build();
    }

}
