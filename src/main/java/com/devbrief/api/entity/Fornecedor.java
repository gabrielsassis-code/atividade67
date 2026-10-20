package com.devbrief.api.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="fornecedores")
public class Fornecedor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String nome;
 @Email @NotBlank @Column(unique=true) private String email;
 @NotBlank @Pattern(regexp="^[0-9()+.\\- ]{8,20}$", message="telefone deve conter de 8 a 20 caracteres válidos") private String telefone;
 @OneToMany(mappedBy="fornecedor") @JsonIgnore private List<Produto> produtos = new ArrayList<>();
}
