package com.example;

import com.example.model.Address;
import com.example.model.Client;
import com.example.repository.AddressRepository;
import com.example.repository.ClientRepository;
import com.example.service.impl.PrintService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest
//@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class RepositoriesTests {

    @Autowired
    private ClientRepository clientRepository;
    private EntityManager entityManager;
    @Autowired
    private AddressRepository addressRepository;
    private PrintService printService;

    private Client client1;
    private Client client2;
    private Client client3;
    private Client client4;
    private Client client5;
    private Address address1;
    private Address address2;
    private Address address3;
    private Address address4;
    private Address address5;
    private Address address6;
    private Address address7;
    @BeforeEach
    void setUp() {
        address1 = new Address("Poland");
        address2 = new Address("Belgium");
        address3 = new Address("Netherlands");
        address4 = new Address("Germany");
        address5 = new Address("USA");
        address6 = new Address("Canada");
        address7 = new Address("Spain");

        client1 = Client.builder()
                .name("Adam")
                .surname("Kowalski")
                .peselNumber("012345678")
                .build();
        client2 = Client.builder()
                .name("Jane")
                .surname("Zabsaw")
                .peselNumber("12345678")
                .build();
        client3 = Client.builder()
                .name("Max")
                .surname("Zabsaw")
                .peselNumber("012345679")
                .build();
        client4 = Client.builder()
                .name("Steve")
                .surname("Wonder")
                .peselNumber("98765432")
                .build();
        client5 = Client.builder()
                .name("Steve")
                .surname("Wonder")
                .peselNumber("67693232")
                .build();

        client1.setAddressSet(Set.of(address1, address2));
        client2.setAddressSet(Set.of(address3));
        client3.setAddressSet(Set.of(address4, address5));
        client4.setAddressSet(Set.of(address6));
        client5.setAddressSet(Set.of(address7));

        clientRepository.saveAll(List.of(client1, client2, client3, client4, client5));
        prepareData();
    }

    @Test
    public void whenClientIsDeleted_thenDeleteAddress() {

        clientRepository.deleteById(1L);
        int actualLength = addressRepository.findAll().size();

        assertEquals(5,actualLength );

    }

    void prepareData() {


//       address2 = new Address("Belgium");
//       address3 = new Address("Netherlands");
//       address4 = new Address("Germany");
//       address5 = new Address("USA");
//       address6 = new Address("Canada");
//       address7 = new Address("Spain");
//
//         client1 = Client.builder()
//                .name("Adam")
//                .surname("Kowalski")
//                .peselNumber("012345678")
//                .build();
//        client2 = Client.builder()
//                .name("Jane")
//                .surname("Zabsaw")
//                .peselNumber("12345678")
//                .build();
//        client3 = Client.builder()
//                .name("Max")
//                .surname("Zabsaw")
//                .peselNumber("012345679")
//                .build();
//        client4 = Client.builder()
//                .name("Steve")
//                .surname("Wonder")
//                .peselNumber("98765432")
//                .build();
//        client5 = Client.builder()
//                .name("Steve")
//                .surname("Wonder")
//                .peselNumber("67693232")
//                .build();
//
//        client1.setAddressSet(Set.of(address1, address2));
//        client2.setAddressSet(Set.of(address3));
//        client3.setAddressSet(Set.of(address4, address5));
//        client4.setAddressSet(Set.of(address6));
//        client5.setAddressSet(Set.of(address7));
//
//        clientRepository.saveAll(List.of(client1, client2, client3, client4, client5));
    }
}

