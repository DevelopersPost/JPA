package org.example.repositories;

import org.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.name like %:#{#name}%")
    List<Employee> findByNameWithSpelExpression(@Param("name") String name);

    // uncomment and use the below query to escape wild cards

    /* @Query("select e from Employee e where e.name like %:#{escape(#name)}%  escape :#{escapeCharacter()}")
    List<Employee> findByNameWithSpelExpression(@Param("name") String name); */



}
