package com.example.dto;

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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientUpdateRequest implements Serializable {

    @NotEmpty
    @Size(max = 30)
    private String nameInput;
    @NotEmpty
    @Size(max = 30)
    private String surnameInput;
    @NotEmpty
    @Size(min = 11,max = 12)
    private String peselNumberInput;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDateInput;
    @NotNull
    private Set<Address> addressesInput;
}
