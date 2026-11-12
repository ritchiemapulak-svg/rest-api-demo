package com.spring.spring_rest.service;

import java.util.List;

import com.spring.spring_rest.dto.StudentDto;

/**
 * Service layer contract for student operations.
 * Holds the business rules of the application and sits between
 * the controller and the repository.
 */
public interface StudentService {
    StudentDto createStudent(StudentDto theStudentDto);
    StudentDto getStudentById(Long theId);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(Long theId, StudentDto theStudentDto);
    StudentDto patchStudent(Long theId, StudentDto theStudentDto);
    void deleteStudentById(Long theId);
}
