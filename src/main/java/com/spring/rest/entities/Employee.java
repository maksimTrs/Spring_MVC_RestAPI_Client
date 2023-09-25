package com.spring.rest.entities;


import lombok.*;
import java.math.BigInteger;



@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor  // custom constructor for all fields with @NonNull
public class Employee {


    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String department;
    @NonNull
    private BigInteger salary;
}
