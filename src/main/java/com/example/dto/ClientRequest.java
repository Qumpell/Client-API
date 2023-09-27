package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRequest implements Serializable {

    @NotEmpty
    @Size(max = 30)
    private String loginInput;
    @NotEmpty
    @Size(max = 20)
    private String nameInput;
    @NotEmpty
    private String surnameInput;
    @NotEmpty
    @Size(min = 11,max = 11)
    private String clientIdInput;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDateInput;
}
