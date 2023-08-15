package com.example.model;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "clients", uniqueConstraints = {@UniqueConstraint(columnNames = {"peselNumber"})})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String peselNumber;

    private LocalDate birthDate;

    private String generation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Set<Address> addressSet;


}
