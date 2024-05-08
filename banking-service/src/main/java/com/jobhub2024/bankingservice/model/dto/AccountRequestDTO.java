package com.jobhub2024.bankingservice.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountRequestDTO {
    @NotBlank(message = "El numero de cuenta no puede estra vacio")
    @Size(min=5, max=20, message = "El numero de cuenta debe tener entre 5 y 20 caracteres")
    @Pattern(regexp = "[0-9]+",message= "El numero de cuentra debe contener solo digitos")
    private String accountNumber;

    @NotNull(message = "El saldo no puede estar vacio")
    private BigDecimal balance;

    @NotBlank(message = "El nombre del titular no puede estar vacio")
    private String ownerName;

    @NotBlank(message = "El correo electronico no puede estar vacio")
    @Email
    private String ownerEmail;
}
