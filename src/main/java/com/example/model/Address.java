package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String country;

//    @OneToOne(mappedBy = "address")
//    private Client client;

//    @Override
//    public String toString() {
//        return "Address{" +
//                "id=" + id +
//                ", country='" + country + '\'' +
////                ", client=" + client.getName() +
//                '}';
//    }
}
