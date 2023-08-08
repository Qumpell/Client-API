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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest
//@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
//We can also use @DataJpaTest if you want to test JPA applications. By default it will configure
// an in-memory embedded database, scan for
// @Entity classes and configure Spring Data JPA repositories.
// Regular @Component beans will not be loaded into the ApplicationContext.
class RepositoriesTests {

    @Autowired
    private ClientRepository clientRepository;
    private EntityManager entityManager;
    @Autowired
    private AddressRepository addressRepository;
    private PrintService printService;

    @BeforeEach
    void setUp() {
        prepareData();
    }

    @Test
    public void whenClientIsDeleted_thenDeleteAddress() {

        //     clientRepository.findAll();
        clientRepository.deleteById(1L);
        int actualLength = addressRepository.findAll().size();

        assertEquals(5,actualLength );

    }

    void prepareData() {

        Address address1 = new Address("Poland");
        Address address2 = new Address("Belgium");
        Address address3 = new Address("Netherlands");
        Address address4 = new Address("Germany");
        Address address5 = new Address("USA");
        Address address6 = new Address("Canada");
        Address address7 = new Address("Spain");
//        addressRepository.saveAll(List.of(address1, address2, address3, address4, address5));


        Client client1 = new Client("Adam", "Kowalski", "012345678");
        Client client2 = new Client("Jane", "Zabsaw", "12345678");
        Client client3 = new Client("Max", "Zabsaw", "012345679");
        Client client4 = new Client("Steve", "Wonder", "98765432");
        Client client5 = new Client("Steve", "Wonder", "67693232");

        client1.setAddressSet(Set.of(address1, address2));
        client2.setAddressSet(Set.of(address3));
        client3.setAddressSet(Set.of(address4, address5));
        client4.setAddressSet(Set.of(address6));
        client5.setAddressSet(Set.of(address7));

        clientRepository.saveAll(List.of(client1, client2, client3, client4, client5));
    }
}

