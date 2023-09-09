package com.example.domain.client.mapper;

import com.example.dto.ClientDTO;
import com.example.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    @Mapping(target = "name", source = "nameInput")
    @Mapping(target = "surname", source = "surnameInput")
    @Mapping(target = "peselNumber", source = "clientIdInput")
    @Mapping(target = "birthDate", source = "birthDateInput")
    Client fromClientDTO(ClientDTO clientDTO);
}
