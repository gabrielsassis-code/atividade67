package com.devbrief.api.dto.itempedido;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ItemPedidoRequestDTO(@NotNull @Positive Long pedidoId, @NotNull @Positive Long produtoId, @NotNull @Positive Integer quantidade, @NotNull @DecimalMin(value="0.0", inclusive=false) BigDecimal precoUnitario) {}
