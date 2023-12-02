package soa.labs.kosiuk.homeworkrecordingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.labs.kosiuk.homeworkrecordingservice.facade.TaskFacade;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskIdReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.task.TaskUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskFacade taskFacade;

    @Autowired
    public TaskController(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @PostMapping
    public ResponseEntity<TaskReadData> createTask(@RequestBody TaskCreateData taskCreateData) {
        HttpResponseWrapper<TaskReadData> createdTaskHttpWrapper = taskFacade.createTask(taskCreateData);
        return ResponseEntity.status(createdTaskHttpWrapper.getHttpStatus())
                .body(createdTaskHttpWrapper.getData());
    }

    @GetMapping
    public ResponseEntity<List<TaskReadData>> getAllTasks(@RequestParam(name = "studentId", required = false) List<UUID> studentsIds,
                                                          @RequestParam(name = "subjectId", required = false) List<UUID> subjectsIds) {
        HttpResponseWrapper<List<TaskReadData>> tasksHttpWrapper;
        if (studentsIds == null && subjectsIds == null) {
            tasksHttpWrapper = taskFacade.getAllTasks();
        } else if (studentsIds != null) {
            tasksHttpWrapper = taskFacade.getTasksByStudents(studentsIds);
        } else {
            tasksHttpWrapper = taskFacade.getTasksBySubjects(subjectsIds);
        }
        return ResponseEntity.status(tasksHttpWrapper.getHttpStatus())
                .body(tasksHttpWrapper.getData());
    }

    @GetMapping("/{studentId}/{subjectId}/{id}")
    public ResponseEntity<TaskReadData> getTaskById(@PathVariable("studentId") UUID studentId,
                                                    @PathVariable("subjectId") UUID subjectId,
                                                    @PathVariable("id") UUID id)  {
        HttpResponseWrapper<TaskReadData> taskHttpWrapper = taskFacade.getTaskById(studentId, subjectId, id);
        return ResponseEntity.status(taskHttpWrapper.getHttpStatus())
                .body(taskHttpWrapper.getData());
    }

    @PutMapping("/{studentId}/{subjectId}/{id}")
    public ResponseEntity<TaskReadData> updateTask(@PathVariable("studentId") UUID studentId,
                                                   @PathVariable("subjectId") UUID subjectId,
                                                   @PathVariable("id") UUID id,
                                                   @RequestBody TaskUpdateData taskUpdateData) {
        HttpResponseWrapper<TaskReadData> updatedTaskHttpWrapper = taskFacade.updateTask(taskUpdateData);
        return ResponseEntity.status(updatedTaskHttpWrapper.getHttpStatus())
                .body(updatedTaskHttpWrapper.getData());
    }

    @DeleteMapping("/{studentId}/{subjectId}/{id}")
    public ResponseEntity<TaskIdReadData> deleteTask(@PathVariable("studentId") UUID studentId,
                                                     @PathVariable("subjectId") UUID subjectId,
                                                     @PathVariable("id") UUID id) {
        HttpResponseWrapper<TaskIdReadData> deletedTaskHttpWrapper = taskFacade.deleteTaskById(studentId, subjectId, id);
        return ResponseEntity.status(deletedTaskHttpWrapper.getHttpStatus())
                .body(deletedTaskHttpWrapper.getData());
    }

}
