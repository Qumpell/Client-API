package com.example.auth;

import com.example.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {

    @NotEmpty
    @Size(max = 30)
    private String firstname;
    @NotEmpty
    @Size(max = 30)
    private String lastname;
    @NotEmpty
    @Size(max = 30)
    private  String login;
    @NotEmpty
    @Size(max = 30)
    private String password;
    @NotEmpty
    @Size(min = 11,max = 12)
    private String peselNumber;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotNull
    private Set<Address> addressSet;
}
