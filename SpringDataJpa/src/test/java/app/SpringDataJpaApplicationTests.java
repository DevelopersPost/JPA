package app;

import app.model.User;
import app.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpringDataJpaApplicationTests {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void beforeAll(){
        userRepository.saveAll(generateUsers());
    }

    private  static List<User> generateUsers(){
        List<User> users = new ArrayList<>();
        User john = new User("john", LocalDate.of(2020, Month.APRIL, 13));
        User john2 = new User("john2", LocalDate.of(2020, Month.APRIL, 13));
        User john3 = new User("john3", LocalDate.of(2020, Month.APRIL, 13));
        User john4 = new User("john4", LocalDate.of(2020, Month.APRIL, 13));
        User john5 = new User("john5", LocalDate.of(2020, Month.APRIL, 13));
        User john6 = new User("john6", LocalDate.of(2020, Month.APRIL, 13));
        User john7 = new User("john7", LocalDate.of(2020, Month.APRIL, 13));
        User john8 = new User("john8", LocalDate.of(2020, Month.APRIL, 13));
        User john9 = new User("john9", LocalDate.of(2020, Month.APRIL, 13));
        User john10 = new User("john10", LocalDate.of(2025, Month.APRIL, 13));

        john.setEmail("john@somedomain.com");
        john.setLevel(1);
        john.setActive(true);
        users.add(john);

        john2.setEmail("john2@somedomain.com");
        john2.setLevel(2);
        john2.setActive(true);
        users.add(john2);

        john3.setEmail("john3@somedomain.com");
        john3.setLevel(3);
        john3.setActive(true);
        users.add(john3);

        john4.setEmail("john4@somedomain.com");
        john4.setLevel(4);
        john4.setActive(true);
        users.add(john4);

        john5.setEmail("john5@somedomain.com");
        john5.setLevel(5);
        john5.setActive(true);
        users.add(john5);

        john6.setEmail("john6@somedomain.com");
        john6.setLevel(6);
        john6.setActive(true);
        users.add(john6);

        john7.setEmail("john7@somedomain.com");
        john7.setLevel(7);
        john7.setActive(true);
        users.add(john7);

        john8.setEmail("john8@somedomain.com");
        john8.setLevel(8);
        john8.setActive(true);
        users.add(john8);

        john9.setEmail("john9@somedomain.com");
        john9.setLevel(9);
        john9.setActive(true);
        users.add(john9);

        john10.setEmail("john10@somedomain.com");
        john10.setLevel(10);
        john10.setActive(true);
        users.add(john10);

        return users;
    }

    @AfterAll
    void afterAll(){
        userRepository.deleteAll();
    }
}
