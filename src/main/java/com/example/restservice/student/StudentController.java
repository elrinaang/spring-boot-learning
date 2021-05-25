package com.example.restservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This class will hold all the resources for the API for Student class

/**
 * The controller acts as an API layer that communicates between the FE and the BE (service layer)
 * When you want to get information from the service layer, create a function and call the service layer
 */

@RestController
@RequestMapping(path = "api/v1/students")

public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //In this function, the FE will like to get a list of students, so we call the service layer which will query the databse
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    //Use RequestBody because you take the student from the front end
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void editStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email){
        studentService.editStudent(studentId, name, email);
    }
}
