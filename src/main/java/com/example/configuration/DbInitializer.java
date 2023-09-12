package com.example.configuration;

import com.example.model.Address;
import com.example.model.Client;
import com.example.repository.AddressRepository;
import com.example.repository.ClientRepository;
import com.example.service.ClientService;
import com.example.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static com.example.repository.ClientRepository.*;



@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@AllArgsConstructor
//@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
public class DbInitializer implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientServiceImpl clientService;

    @Override
    public void run(String... args) throws Exception {
//        Client client1 = new Client("Adam", "Kowalski","012345678", LocalDate.of(1955, Month.JANUARY,12));
//        Client client2 = new Client("Jane","Zabsaw","12345678",LocalDate.of(2001, Month.MAY,11));
//        Client client3 = new Client("Max","Zabsaw","012345679",LocalDate.of(2010, Month.FEBRUARY,22));
//        Client client4 = new Clien,t("Steve","Wonder","98765432",LocalDate.of(1978, Month.SEPTEMBER,1));
//        Client client5 = new Client("Steve","Wonder","67693232",LocalDate.of(1968, Month.MARCH,30));

        Client client = Client.builder()
                .login("test")
                .name("KEK")
                .surname("SSS")
                .peselNumber("12345678")
                .birthDate(LocalDate.of(1994,Month.AUGUST,12))
                .build();
        Client client1 = client.toBuilder().build();
        client1.setLogin("a");
        Client client2 = client.toBuilder().build();
        client2.setLogin("b");
        Client client3 = client.toBuilder().build();
        client3.setLogin("c");
        Client client4 = client.toBuilder().build();
        client4.setLogin("d");
        Client client5 = client.toBuilder().build();
        client5.setLogin("e");

        Address address1 = Address.builder().country("Poland").build();
        client.setAddressSet(Set.of(address1));
        clientRepository.save(client);
        clientRepository.saveAll(Set.of(client1,client2,client3,client4,client5));
//        Address address2 = new Address("Belgium");
//        Address address3 = new Address("Netherlands");
//        Address address4 = new Address("Germany");
//        Address address5 = new Address("USA");
//        Address address6 = new Address("Canada");
//        Address address7 = new Address("Spain");
//        client1.setAddress(address1);
//        client2.setAddress(address2);
//        client3.setAddress(address3);
//        address1.setClient(client1);
//        address2.setClient(client2);
//        address3.setClient(client3);
//        client1.setAddressSet(Set.of(address1,address2));
//        client2.setAddressSet(Set.of(address3));
//        client3.setAddressSet(Set.of(address4,address5));
//        client4.setAddressSet(Set.of(address6));
//        client5.setAddressSet(Set.of(address7));

//        addressRepository.saveAll(List.of(address1, address2, address3, address4, address5));
//        clientRepository.saveAll(List.of(client1, client2, client3,client4,client5));
//          clientService.create(client1);
//          clientService.create(client2);
//          clientService.create(client3);
//          clientService.create(client4);
//          clientService.create(client5);

//        clientRepository.findAll().forEach(c -> System.out.println(c.getAddressSet()));
//        addressRepository.deleteById(1L);

//        clientRepository.deleteById(1L);
   //     System.out.println(clientRepository.findAll());
//        client1.setAddressSet(Collections.emptySet());
//        clientRepository.delete(client1);
//        System.out.println(clientRepository.findById(6L));
//        addressRepository.deleteById(1L);
//        System.out.println(clientRepository.findAll());

     //   System.out.println(clientRepository.findAllUsersByName("Jane"));

//        System.out.println(clientRepository.findAll(hasName("Steve").and(hasSurname("Wonder").and(hasPesel("67693232")))));
//
//        var onePesel = clientRepository.exists(hasName("Steve"));
//        System.out.println(onePesel);
//        clientService.create(client);

     //   System.out.println(clientRepository.findAll(hasCountry("Poland")));
    }
}
