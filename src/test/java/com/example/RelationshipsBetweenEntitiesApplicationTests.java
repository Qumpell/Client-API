package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class RelationshipsBetweenEntitiesApplicationTests {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Test
   public void whenClientIsDeleted_thenDeleteAddress(){
        Client client = new Client("Jan");
        Address address = new Address("Poland");

        address.setClient(client);

        addressRepository.save(address);
//        entityManager.persist(address);

        assertNotNull(client.getId());
        assertNotNull(address.getId());

//        entityManager.remove(client);
        clientRepository.delete(client);
//        addressRepository.delete(address);
//        entityManager.flush();
//        entityManager.clear();

        List<Client> clientList = clientRepository.findAll();
        List<Address> addressList = addressRepository.findAll();
//        assertEquals(1,clientList.size(),);
        assertEquals(1,addressList.size());


    }

}
