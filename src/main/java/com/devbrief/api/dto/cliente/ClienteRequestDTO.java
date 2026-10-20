package com.devbrief.api.dto.cliente;
import jakarta.validation.constraints.*;
public record ClienteRequestDTO(@NotBlank(message="nome é obrigatório") String nome, @NotBlank @Email(message="email inválido") String email, @NotBlank @Pattern(regexp="^[0-9()+.\\- ]{8,20}$", message="telefone inválido") String telefone) {}
