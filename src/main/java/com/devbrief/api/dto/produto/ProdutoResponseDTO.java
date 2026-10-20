package com.devbrief.api.dto.produto;
import java.math.BigDecimal;
public record ProdutoResponseDTO(Long id, String nome, Integer quantidade, BigDecimal preco, Long categoriaId, String categoriaNome, Long fornecedorId, String fornecedorNome) {}
