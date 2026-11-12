package com.spring.spring_rest.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * REST controller exposing CRUD endpoints for students under
 * {@code /api/students}. It receives HTTP requests, delegates the
 * work to {@link StudentService}, and returns the result with an
 * HTTP status.
 */
@RestController 
@RequestMapping("/api/students") 
public class StudentRestController {

    private StudentService studentService;

     /**
     * Creates the controller with its service (constructor injection).
     *
     * @param studentService the service that handles student operations
     */
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Adds a new student. {@code POST /api/students}
     *
     * @param theStudentDto the student JSON body, validated
     * @return the saved student with HTTP status 201 (Created)
     */
    @PostMapping 
    public ResponseEntity<StudentDto>
        createStudents(@Valid @RequestBody StudentDto theStudentDto){
        StudentDto saved = studentService.createStudent(theStudentDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

     /**
     * Gets one student. {@code GET /api/students/{id}}
     *
     * @param id the id of the student
     * @return the student with HTTP status 200 (OK)
     */
    @GetMapping("{id}")
    public ResponseEntity<StudentDto>getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }
    
    /**
     * Gets all students. {@code GET /api/students}
     *
     * @return the list of students with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    /**
     * Replaces a student's data. {@code PUT /api/students/{id}}
     *
     * @param id the id of the student to update
     * @param theStudentDto the new student data, validated
     * @return the updated student with HTTP status 200 (OK)
     */
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, 
        @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto studentDto = 
        studentService.updateStudent(id, theStudentDto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Updates only the sent fields of a student.
     * {@code PATCH /api/students/{id}}
     *
     * @param id the id of the student to update
     * @param theStudentDto the fields to change, validated
     * @return the updated student with HTTP status 200 (OK)
     */
    @PatchMapping("{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id,
        @Valid @RequestBody StudentDto theStudentDto){
        StudentDto studentDto = studentService.patchStudent(id, theStudentDto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Deletes a student. {@code DELETE /api/students/{id}}
     *
     * @param id the id of the student to delete
     * @return a success message with HTTP status 200 (OK)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

}
