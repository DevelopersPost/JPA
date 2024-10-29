package com.example;

import com.example.model.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    CommandLineRunner paginationTest(){
        return (x) -> {

            int currentPage = 0;
            int pageSize = 3;
            Page<User> userPage;

            do {
                userPage = userRepository.findAll(PageRequest.of(currentPage,pageSize));

                 System.out.println("Page: " + (currentPage + 1) + " of " + userPage.getTotalPages());
                 userPage.getContent().forEach(user -> System.out.println(user.getName() + " - " + user.getEmail()));

                currentPage++;
            }while (userPage.hasNext());

            List<User> usersByName = userRepository.findByName("Duplicate User", PageRequest.of(0, 4, Sort.by("name")));
            usersByName.forEach(u -> System.out.println(u.getName() + " - " + u.getEmail() + " - " + u.getDob()));

            User user1 = userRepository.findFirstByOrderByNameAsc();

            User user2 = userRepository.findTopByOrderByDobDesc();

            System.out.println("findFirstByOrderByNameAsc:"+user1);

            System.out.println("findTopByOrderByDobDesc:"+user2);

            List<User> usersFirst2ByDob = userRepository.findFirst2ByName("Duplicate User",Sort.by("name"));

             usersFirst2ByDob.forEach(u -> System.out.println(u.getName() + " - " + u.getEmail() + " - " + u.getDob()));

            Sort.TypedSort<User> user = Sort.sort(User.class);
            List<User> usersByFirstName = userRepository.findByName("Duplicate User",user.by(User::getName).descending());

            usersByFirstName.forEach(ubfn -> System.out.println(ubfn.getName() + " - " + ubfn.getEmail()));


        };
    }
}
