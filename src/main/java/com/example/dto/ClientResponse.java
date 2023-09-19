package com.example.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse extends RepresentationModel<ClientResponse> {

    private String login;
    private String name;
    private String surname;
    private String generation;

}
