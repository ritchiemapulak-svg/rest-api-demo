package com.spring.spring_rest.mapper;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;

/**
 * Utility class that converts between the {@link Student} entity
 * (database model) and the {@link StudentDto} (data sent to and
 * from the client).
 */
public class StudentMapper {

    /**
     * Converts a {@link Student} entity into a {@link StudentDto}.
     *
     * @param theStudent the entity to convert
     * @return a DTO with the same id, first name, last name, and email
     */
    public static StudentDto mapToStudentDto(Student theStudent){
        return new StudentDto(theStudent.getId(), 
                            theStudent.getFirstName(), 
                            theStudent.getLastName(), 
                            theStudent.getEmail()
        );
    }

    /**
     * Converts a {@link StudentDto} into a {@link Student} entity
     * that is ready to be saved to the database.
     *
     * @param theStudentDto the DTO to convert
     * @return an entity with the same id, first name, last name, email
     */
    public static Student mapToStudent(StudentDto theStudentDto){
        return new Student(theStudentDto.getId(),
                            theStudentDto.getFirstName(), 
                            theStudentDto.getLastName(), 
                            theStudentDto.getEmail());
    }
}
