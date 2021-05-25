package com.example.restservice.student;

/**
 * naming convention for anything that accesses the database and when you are working with JPA
 * represents the data access layer
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//first argument is the type that we want the repo to work upon --> since ID is long, the second argument is long
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //want to find a user by email --> This transforms into SQL of SELECT * FROM student WHERE email = param
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

}
