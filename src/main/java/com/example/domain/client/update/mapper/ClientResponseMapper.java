package com.example.domain.client.update.mapper;

import com.example.dto.ClientResponse;
import com.example.model.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientResponseMapper {
    @Mapping(target = "login", source = "login")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "generation", source = "generation")
    ClientResponse toClientResponse(Client client);
}
