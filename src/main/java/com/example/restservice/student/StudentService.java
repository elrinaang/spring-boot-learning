package com.example.restservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        //returns the list of students
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void editStudent(Long studentId, String name, String email) {
        //find the student, throw an error if we cannot find the student
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + "does not exist"));

        //update the name if there is a name param
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            //change the name of the student
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }
    }
}
