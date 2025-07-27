package com.springbootTest.repository;

import com.springbootTest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    Optional<Employee> findByEmail(String email);

    //here write the query on Employee entity
    @Query("select e from Employee e where e.firstName=?1 AND e.lastName=?2")
    Employee findByJPQLQuery(String firstname,String lastName);

    //here write the query on Employee entity
    @Query("select e from Employee e where e.firstName=:firstName AND e.lastName=:lastName")
    Employee findByJPQLQNamedParams(@Param("firstName") String firstname, @Param("lastName")String lastName);

    //Native sql query we actually write on employees database table not Employee class
    @Query(value = "select * from employees e where e.first_name=?1 AND e.last_name=?2",nativeQuery = true)
    Employee findByNativeSqlWithIndex( String firstname, String lastName);

    @Query(value = "select * from employees e where e.first_name=:firstName AND e.last_name=:lastName",nativeQuery = true)
    Employee findByNativeSqlWithName(@Param("firstName") String firstname, @Param("lastName")String lastName);


}
