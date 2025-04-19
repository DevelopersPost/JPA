package org.example;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;

import java.util.List;

@SpringBootApplication
public class Main {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Bean
    CommandLineRunner runner(UserRepository repository) {
        return args -> {
            // Example - 1

            User probe1 = new User();
            probe1.setName("Alice");

            Example<User> example1 = Example.of(probe1);
            List<User> result1 = userRepository.findAll(example1);
            result1.forEach(System.out::println);


            // Example - 2

            User probe2 = new User();
            probe2.setName("Al");
            probe2.setCity("Boston");

            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withIgnorePaths("id")
                    .withMatcher("name", match -> match.startsWith())
                    .withMatcher("city", match -> match.exact());

            Example<User> example2 = Example.of(probe2, matcher);

            List<User> result2 = userRepository.findAll(example2);
            result2.forEach(System.out::println);



            // Example - 3

            User probe3 = new User();
            probe3.setName("Al");
            probe3.setCity("Boston");

            ExampleMatcher matcher1 = ExampleMatcher.matching()
                    .withIgnorePaths("id")
                    .withMatcher("name", match -> match.startsWith())
                    .withMatcher("city", match -> match.exact());

            Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
            Example<User> example3 = Example.of(probe3, matcher1);
            Page<User> page = userRepository.findAll(example3, pageable);

            System.out.println("Total Elements: " + page.getTotalElements());
            System.out.println("Total Pages: " + page.getTotalPages());
            System.out.println("Current Page: " + page.getNumber());

            page.getContent().forEach(System.out::println);


        };
    }
}