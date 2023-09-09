//package com.example.unit;
//
//import com.example.model.Address;
//import com.example.model.Client;
//import com.example.repository.ClientRepository;
//import com.example.service.ClientService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class ClientServiceTest {
//
//    @InjectMocks
//    private ClientService clientService;
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @Test
//    void should_Create_ClientHistory_When_Client_Is_Created(){
//       //given
//        final var clientAddress = Address.builder().country("Poland").build();
//        final var clientToCreate = Client.builder()
//                .name("Jan")
//                .surname("Kowalski").peselNumber("12345678")
//                .addressSet(Set.of(clientAddress))
//                .birthDate(LocalDate.of(1999, Month.AUGUST,8))
//                .build();
//        when(clientRepository.save(any(Client.class))).thenReturn(clientToCreate);
//
//        //
//    }
//
//}
