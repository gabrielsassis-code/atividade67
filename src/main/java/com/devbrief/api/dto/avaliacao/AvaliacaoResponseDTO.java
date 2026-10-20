package com.devbrief.api.dto.avaliacao;
public record AvaliacaoResponseDTO(Long id, Long clienteId, String clienteNome, Long produtoId, String produtoNome, Integer nota, String comentario) {}
