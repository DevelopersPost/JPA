package app;

import app.model.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import app.repositories.UserRepository;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@ComponentScan
public class SpringDataJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class,args);
    }

    // @Bean
    public ApplicationRunner configure(UserRepository userRepository){
        return env -> {
            User user1 = new User("x", LocalDate.of(
                    2020, Month.APRIL,1
            ));

            User user2 = new User("y", LocalDate.of(
                    2021, Month.MAY,2
            ));

            userRepository.save(user1);
            userRepository.save(user2);

            userRepository.findAll().forEach(System.out::println);
        };
    }
}
