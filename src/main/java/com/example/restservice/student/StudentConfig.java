package com.example.restservice.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Miriam", LocalDate.of(2000, Month.JANUARY,5), "Miriam@gmail.com"
            );

            Student bob = new Student(
                    "Bob", LocalDate.of(2004, Month.JANUARY,12),"Bob@gmail.com"
            );

            repository.saveAll(
                    Arrays.asList(mariam,bob)
            );
        };
    }
}
