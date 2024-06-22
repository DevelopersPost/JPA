package app;

import app.model.Projection;
import app.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindUsersUsingQueriesTest extends SpringDataJpaApplicationTests {

    @Test
    void testFindAll(){
        List<Projection.UsernameOnly> users = userRepository.findByEmail("john@somedomain.com");
        List<Projection.UserSummery> byRegistrationDateAfter = userRepository.findByRegistrationDateAfter(LocalDate.of(2024, Month.JUNE, 22));
        List<Projection.UsernameOnly> byEmail = userRepository.findByEmail("john@somedomain.com", Projection.UsernameOnly.class);
        List<User> byEmail1 = userRepository.findByEmail("john@somedomain.com", User.class);
        System.out.println(byRegistrationDateAfter.get(0).getInfo());
        System.out.println(byRegistrationDateAfter.get(0).getUsername());
        assertAll(
                () -> assertEquals(1, users.size())
        );
    }


}
