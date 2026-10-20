package com.devbrief.api.dto.pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public record PedidoResponseDTO(Long id, LocalDateTime data, String status, Long clienteId, String clienteNome, BigDecimal total, List<Item> itens) {
 public record Item(Long itemId, Long produtoId, String produtoNome, Integer quantidade, BigDecimal precoUnitario, BigDecimal subtotal) {}
}
