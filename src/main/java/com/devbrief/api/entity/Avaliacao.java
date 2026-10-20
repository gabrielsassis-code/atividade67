package com.devbrief.api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="avaliacoes", uniqueConstraints=@UniqueConstraint(columnNames={"cliente_id","produto_id"}))
public class Avaliacao {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="cliente_id") private Cliente cliente;
 @ManyToOne(optional=false) @JoinColumn(name="produto_id") private Produto produto;
 @NotNull @Min(1) @Max(5) private Integer nota;
 @NotBlank @Column(length=1000) private String comentario;
}
