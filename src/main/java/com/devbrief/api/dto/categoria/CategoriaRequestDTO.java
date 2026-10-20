package com.devbrief.api.dto.categoria;
import jakarta.validation.constraints.*;
public record CategoriaRequestDTO(@NotBlank(message="nome não pode ser vazio") String nome, @NotBlank(message="descrição não pode ser vazia") String descricao) {}
