package com.example.server.entity;

import com.example.server.entity.enums.RolePerson;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    private Long cardId;

    @Column(name="user_name", unique = true, nullable = false, length = 100)
    private String userName;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name="address", nullable = false, length = 100)
    private String address;

    @Column(name="phone", unique = true,length = 20)
    private String phone;

    @Column(name="email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name="password", nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolePerson rolePerson;

}

