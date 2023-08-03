package com.example;

import com.example.model.Address;
import com.example.model.Client;
import com.example.repository.AddressRepository;
import com.example.repository.ClientRepository;
import com.example.service.impl.PrintService;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest()
@ActiveProfiles("test")
class RelationshipsBetweenEntitiesApplicationTests {


    private ClientRepository clientRepository;
    private EntityManager entityManager;
    private AddressRepository addressRepository;
    private PrintService printService;

    @BeforeEach
    void setUp(){
        prepareData();
    }

    @Test
    public void whenClientIsDeleted_thenDeleteAddress() {

   //     clientRepository.findAll();

        assertEquals(4, 4);

    }

    void prepareData() {

        Address address1 = new Address("Poland");
//        Address address2 = new Address("Belgium");
//        Address address3 = new Address("Netherlands");
//        Address address4 = new Address("Germany");
//        Address address5 = new Address("USA");
//        Address address6 = new Address("Canada");
//        Address address7 = new Address("Spain");
//        addressRepository.saveAll(List.of(address1, address2, address3, address4, address5));
        addressRepository.save(address1);


//        Client client1 = new Client("Adam", "Kowalski", "012345678");
//        Client client2 = new Client("Jane", "Zabsaw", "12345678");
//        Client client3 = new Client("Max", "Zabsaw", "012345679");
//        Client client4 = new Client("Steve", "Wonder", "98765432");
//        Client client5 = new Client("Steve", "Wonder", "67693232");
//
//        client1.setAddressSet(Set.of(address1, address2));
//        client2.setAddressSet(Set.of(address3));
//        client3.setAddressSet(Set.of(address4, address5));
//        client4.setAddressSet(Set.of(address6));
//        client5.setAddressSet(Set.of(address7));
//
//        clientRepository.saveAll(List.of(client1, client2, client3, client4, client5));
//    }
}}
