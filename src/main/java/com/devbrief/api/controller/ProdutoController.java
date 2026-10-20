package com.devbrief.api.controller;
import com.devbrief.api.service.ProdutoService;
import com.devbrief.api.dto.produto.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/produtos")
public class ProdutoController {
 private final ProdutoService service;
 public ProdutoController(ProdutoService s){service=s;}
 @GetMapping public List<ProdutoResponseDTO> listar(){return service.listar();}
 @GetMapping("/{id}") public ProdutoResponseDTO buscar(@PathVariable Long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO d){return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(d));}
 @PutMapping("/{id}") public ProdutoResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody ProdutoRequestDTO d){return service.atualizar(id,d);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id){service.excluir(id);return ResponseEntity.noContent().build();}
}
