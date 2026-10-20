package com.devbrief.api.dto.avaliacao;
import jakarta.validation.constraints.*;
public record AvaliacaoRequestDTO(@NotNull @Positive Long clienteId, @NotNull @Positive Long produtoId, @NotNull @Min(1) @Max(5) Integer nota, @NotBlank String comentario) {}
