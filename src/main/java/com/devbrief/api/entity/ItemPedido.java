package com.devbrief.api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="itens_pedido")
public class ItemPedido {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="pedido_id") private Pedido pedido;
 @ManyToOne(optional=false) @JoinColumn(name="produto_id") private Produto produto;
 @NotNull @Positive private Integer quantidade;
 @NotNull @DecimalMin(value="0.0", inclusive=false) @Digits(integer=10,fraction=2) private BigDecimal precoUnitario;
}
