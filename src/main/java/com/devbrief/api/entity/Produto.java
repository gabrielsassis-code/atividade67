package com.devbrief.api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="produtos")
public class Produto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String nome;
 @PositiveOrZero private Integer quantidade;
 @NotNull @DecimalMin(value="0.0", inclusive=false) @Digits(integer=10,fraction=2) private BigDecimal preco;
 @ManyToOne(optional=false) @JoinColumn(name="categoria_id") private Categoria categoria;
 @ManyToOne @JoinColumn(name="fornecedor_id") private Fornecedor fornecedor;
 @OneToMany(mappedBy="produto") @JsonIgnore private List<ItemPedido> itensPedido = new ArrayList<>();
 @OneToMany(mappedBy="produto") @JsonIgnore private List<Avaliacao> avaliacoes = new ArrayList<>();
}
