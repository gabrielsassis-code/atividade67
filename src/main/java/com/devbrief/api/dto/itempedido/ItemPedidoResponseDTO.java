package com.devbrief.api.dto.itempedido;
import java.math.BigDecimal;
public record ItemPedidoResponseDTO(Long id, Long pedidoId, Long produtoId, String produtoNome, Integer quantidade, BigDecimal precoUnitario) {}
