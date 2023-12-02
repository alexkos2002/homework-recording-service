package soa.labs.kosiuk.homeworkrecordingservice.facade;

import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentCreateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentReadData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.student.StudentUpdateData;
import soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers.HttpResponseWrapper;

import java.util.List;
import java.util.UUID;

public interface StudentFacade {

    HttpResponseWrapper<StudentReadData> createStudent(StudentCreateData studentCreateData);

    HttpResponseWrapper<List<StudentReadData>> getAllStudents();

    HttpResponseWrapper<StudentReadData> getStudentById(UUID id);

    HttpResponseWrapper<StudentReadData> updateStudent(StudentUpdateData studentUpdateData);

    HttpResponseWrapper<UUID> deleteStudentById(UUID id);

}
