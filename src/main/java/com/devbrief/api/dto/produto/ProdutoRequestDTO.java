package com.devbrief.api.dto.produto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProdutoRequestDTO(@NotBlank String nome, @NotNull @PositiveOrZero Integer quantidade, @NotNull @DecimalMin(value="0.0", inclusive=false) BigDecimal preco, @NotNull @Positive Long categoriaId, Long fornecedorId) {}
