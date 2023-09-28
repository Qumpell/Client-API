package com.example.auth.mapper;

import com.example.auth.RegisterRequest;
import com.example.model.Client;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterRequestMapper {
    @Mapping(target = "name", source = "firstname")
    @Mapping(target = "surname", source = "lastname")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "peselNumber", source = "peselNumber")
    @Mapping(target = "birthDate", source = "birthDate",dateFormat = "yyyy-MM-dd")
    Client fromRegisterRequest(RegisterRequest request, @Context PasswordEncoder passwordEncoder);

    @AfterMapping
    default void map(@MappingTarget Client.ClientBuilder client,
                            RegisterRequest request,
                            @Context PasswordEncoder passwordEncoder){
     client.password(passwordEncoder.encode(request.getPassword()));
    }
}
