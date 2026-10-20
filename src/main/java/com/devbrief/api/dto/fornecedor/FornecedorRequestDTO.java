package com.devbrief.api.dto.fornecedor;
import jakarta.validation.constraints.*;
public record FornecedorRequestDTO(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Pattern(regexp="^[0-9()+.\\- ]{8,20}$") String telefone) {}
