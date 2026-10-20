package com.devbrief.api.dto.pedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record PedidoRequestDTO(@NotNull @Positive Long clienteId, String status, @NotEmpty List<@Valid Item> itens) {
 public record Item(@NotNull @Positive Long produtoId, @NotNull @Positive Integer quantidade) {}
}
