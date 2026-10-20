package com.devbrief.api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="pedidos")
public class Pedido {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotNull private LocalDateTime data = LocalDateTime.now();
 @NotNull @Enumerated(EnumType.STRING) private StatusPedido status = StatusPedido.PENDENTE;
 @ManyToOne(optional=false) @JoinColumn(name="cliente_id") private Cliente cliente;
 @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true) private List<ItemPedido> itens = new ArrayList<>();
 @Transient public BigDecimal getTotal() { return itens.stream().map(i -> i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade()))).reduce(BigDecimal.ZERO, BigDecimal::add); }
 public enum StatusPedido { PENDENTE, PAGO, ENVIADO, ENTREGUE, CANCELADO }
}
