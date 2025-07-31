package com.reactive.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//@Getter --> it will be included by @Data
//@Setter --> it will be included by @Data
//@ToString --> it will be included by @Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//below is to make this class as entity in mongodb
@Document(collection = "employess") //collection is similar to table /table na,e
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
