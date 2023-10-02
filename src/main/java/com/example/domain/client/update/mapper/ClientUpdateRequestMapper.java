package com.example.domain.client.update.mapper;

import com.example.dto.ClientUpdateRequest;
import com.example.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientRequestMapper {
    @Mapping(target = "login", source = "loginInput")
    @Mapping(target = "name", source = "nameInput")
    @Mapping(target = "surname", source = "surnameInput")
    @Mapping(target = "peselNumber", source = "peselNumberInput")
    @Mapping(target = "birthDate", source = "birthDateInput")
    @Mapping(target = "addressSet",source = "addressesInput")
    Client fromClientRequest(ClientUpdateRequest clientRequest);
}
