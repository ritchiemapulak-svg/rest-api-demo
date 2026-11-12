package com.spring.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_rest.entity.Student;

/**
 * Repository for {@link Student} entities.
 * Extending {@link JpaRepository} provides ready-made database methods
 * such as {@code save()}, {@code findById()}, {@code findAll()}, and
 * {@code deleteById()}, so no implementation code is needed. The two
 * type parameters are the entity type ({@code Student}) and the type
 * of its id ({@code Long}).
 */
public interface StudentRepository extends JpaRepository<Student, Long>{
}
