package soa.labs.kosiuk.homeworkrecordingservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Student;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Subject;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.Task;
import soa.labs.kosiuk.homeworkrecordingservice.model.entity.composite.TaskId;
import soa.labs.kosiuk.homeworkrecordingservice.repository.StudentRepository;
import soa.labs.kosiuk.homeworkrecordingservice.repository.SubjectRepository;
import soa.labs.kosiuk.homeworkrecordingservice.repository.TaskRepository;
import soa.labs.kosiuk.homeworkrecordingservice.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultTaskService implements TaskService {

    private TaskRepository taskRepository;

    private StudentRepository studentRepository;

    private SubjectRepository subjectRepository;

    @Autowired
    public DefaultTaskService(TaskRepository taskRepository, StudentRepository studentRepository,
                              SubjectRepository subjectRepository) {
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Optional<Task> createTask(Task task) {
        Optional<Student> taskAssigneeOptional = studentRepository.findById(task.getStudentId());
        Optional<Subject> taskSubjectOptional = subjectRepository.findById(task.getSubjectId());
        if (taskAssigneeOptional.isEmpty() || taskSubjectOptional.isEmpty()) {
            return Optional.empty();
        }
        task.setStudent(taskAssigneeOptional.get());
        task.setSubject(taskSubjectOptional.get());
        task.setId(UUID.randomUUID());
        task.setTimestamp(LocalDateTime.now());
        Task createdTask = taskRepository.save(task);
        return Optional.ofNullable(createdTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(UUID studentId, UUID subjectId, UUID id) {
        TaskId taskId = TaskId.builder()
                .id(id)
                .studentId(studentId)
                .subjectId(subjectId)
                .build();

        return taskRepository.findById(taskId);
    }

    @Override
    public List<Task> getTasksByStudents(List<UUID> studentsIds) {
        return taskRepository.findAllByStudentsIds(studentsIds);
    }

    @Override
    public List<Task> getTasksBySubjects(List<UUID> subjectsIds) {
        return taskRepository.findAllBySubjectsIds(subjectsIds);
    }

    @Override
    public Optional<Task> updateTask(Task task) {
        TaskId taskId = TaskId.builder()
                .id(task.getId())
                .studentId(task.getStudentId())
                .subjectId(task.getSubjectId())
                .build();
        Optional<Task> taskToUpdateOptional = taskRepository.findById(taskId);
        if (taskToUpdateOptional.isPresent()) {
            Optional<Student> taskAssigneeOptional = studentRepository.findById(task.getStudentId());
            Optional<Subject> taskSubjectOptional = subjectRepository.findById(task.getSubjectId());
            if (taskAssigneeOptional.isPresent() && taskSubjectOptional.isPresent()) {
                task.setStudent(taskAssigneeOptional.get());
                task.setSubject(taskSubjectOptional.get());
                task.setTimestamp(LocalDateTime.now());
                return Optional.ofNullable(taskRepository.save(task));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Task> deleteTaskById(UUID studentId, UUID subjectId, UUID id) {
        TaskId taskId = TaskId.builder()
                .id(id)
                .studentId(studentId)
                .subjectId(subjectId)
                .build();
        taskRepository.deleteById(taskId);
        return taskRepository.findById(taskId);
    }
}
