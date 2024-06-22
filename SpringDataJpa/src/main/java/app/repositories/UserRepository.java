package app.repositories;

import app.model.Projection;
import app.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findAllByOrderByUsernameAsc();
    List<User> findByRegistrationDateBetween(LocalDate start,
                                             LocalDate end);
    List<User> findByUsernameAndEmail(String username, String email);
    List<User> findByUsernameOrEmail(String username, String email);
    List<User> findByUsernameIgnoreCase(String username);
    List<User> findByLevelOrderByUsernameDesc(int level);
    List<User> findByLevelGreaterThanEqual(int level);
    List<User> findByUsernameContaining(String text);
    List<User> findByUsernameLike(String text);
    List<User> findByUsernameStartingWith(String start);
    List<User> findByUsernameEndingWith(String end);
    List<User> findByActive(boolean active);
    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);
    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);
    List<User> findFirst2ByLevel(int level, Sort sort);
    List<User> findByLevel(int level, Sort sort);
    List<User> findByActive(boolean active, Pageable pageable);
    Streamable<User> findByEmailContaining(String text);
    Streamable<User> findByLevel(int level);
    @Query("select u from #{#entityName} u where u.username like %?1%")
    List<Object> findByAsArrayAndSort(String text,Sort sort);
    List<Projection.UserSummery> findByRegistrationDateAfter(LocalDate date);
    List<Projection.UsernameOnly> findByEmail(String username);
    <T> List<T> findByEmail(String username, Class<T> type);
}
