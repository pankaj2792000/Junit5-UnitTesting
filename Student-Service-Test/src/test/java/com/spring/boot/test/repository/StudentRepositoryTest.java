package com.spring.boot.test.repository;

import com.spring.boot.test.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

      @Test
        public void givenStudentObject_whenSave_thenReturnStudentObject(){
            //given-preconditon set up
          Student student=Student.builder()
                  .firstName("Pankaj")
                  .lastName("Sunda")
                  .email("p@gmail.com")
                  .build();

            //when -action
    Student student1=studentRepository.save(student);


            //then-verify the output
          Assertions.assertThat(student1).isNotNull();
          Assertions.assertThat(student1.getId()).isGreaterThan(0);

        }
}
