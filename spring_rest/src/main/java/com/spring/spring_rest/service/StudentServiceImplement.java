package com.spring.spring_rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;
import com.spring.spring_rest.exception.StudentNotFoundException;
import com.spring.spring_rest.mapper.StudentMapper;
import com.spring.spring_rest.repository.StudentRepository;

/**
 * Default implementation of {@link StudentService}.
 * Converts between {@link StudentDto} and {@link Student}, applies
 * the business rules, and uses {@link StudentRepository} to access
 * the database.
 */
@Service 
public class StudentServiceImplement implements StudentService{

    private StudentRepository studentRepo;

     /**
     * Creates the service with its dependency (constructor injection).
     *
     * @param studentRepo the repository used to read and write students
     */
    public StudentServiceImplement(StudentRepository studentRepo){
        this.studentRepo= studentRepo;
    }

    /**
     * {@inheritDoc}
     * The DTO is converted to an entity, saved with {@code save()},
     * and the saved entity is converted back to a DTO.
     */
    @Override
    public StudentDto createStudent(StudentDto theStudentDto) {
        Student theStudent= StudentMapper.mapToStudent(theStudentDto);
        Student saved = studentRepo.save(theStudent);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * {@inheritDoc}
     * Looks the student up by id and converts it to a DTO. Throws
     * {@link StudentNotFoundException} if no student has that id.
     */
    @Override
    public StudentDto getStudentById(Long theId) {
        Student student = studentRepo.findById(theId).orElseThrow(()-> 
        new StudentNotFoundException("Student id " + theId + " not found!"));
    return StudentMapper.mapToStudentDto(student);
    }

    /**
     * {@inheritDoc}
     * Loads every student from the database and converts each one
     * to a DTO.
     */
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream().map(
            (student)-> StudentMapper.mapToStudentDto(student))
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     * Behaves like a PUT: first name, last name, and email are all
     * overwritten with the values from the DTO.
     */
    @Override
    public StudentDto updateStudent(Long theId, StudentDto theStudentDto) {
        Student student = studentRepo.findById(theId).orElseThrow(()-> 
        new StudentNotFoundException("Student id " + theId + " not found!")
        );
        student.setFirstName(theStudentDto.getFirstName());
        student.setLastName(theStudentDto.getLastName());
        student.setEmail(theStudentDto.getEmail());

        Student saved = studentRepo.save(student);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * {@inheritDoc}
     * Each field is copied to the entity only if it is not null,
     * so fields that were not sent keep their current values.
     */
    @Override
    public StudentDto patchStudent(Long theId, StudentDto theStudentDto) {
        Student student = studentRepo.findById(theId).orElseThrow(()->
        new StudentNotFoundException("Student id " + theId + " not found!"));

        if(theStudentDto.getFirstName() != null){
            student.setFirstName(theStudentDto.getFirstName());
        }
        if(theStudentDto.getLastName() != null) {
            student.setLastName(theStudentDto.getLastName());
        }
        if(theStudentDto.getEmail() != null)  {
            student.setEmail(theStudentDto.getEmail());
        }
        
        Student saved = studentRepo.save(student);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * {@inheritDoc}
     * Checks that the student exists first, so a missing id gives a
     * clear "not found" error instead of a silent no-op.
     */
    @Override
    public void deleteStudentById(Long theId) {
        studentRepo.findById(theId).orElseThrow(()->  
        new StudentNotFoundException("Student id " + theId + " not found!"));

        studentRepo.deleteById(theId);
    }
    
}
