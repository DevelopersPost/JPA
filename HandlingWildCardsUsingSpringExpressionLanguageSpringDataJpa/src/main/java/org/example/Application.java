package org.example;

import org.example.model.Employee;
import org.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner test() {
        return args -> {

            employeeRepository.findByNameWithSpelExpression("A12B").forEach(System.out::println); // 1 record

            employeeRepository.findByNameWithSpelExpression("A_B").forEach(System.out::println);  // 2 records after 1 record

            employeeRepository.findByNameWithSpelExpression("A%B").forEach(System.out::println); // 3 records after 1 record

        };
    }
}
