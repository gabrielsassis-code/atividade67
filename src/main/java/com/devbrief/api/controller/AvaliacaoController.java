package com.devbrief.api.controller;
import com.devbrief.api.service.AvaliacaoService;
import com.devbrief.api.dto.avaliacao.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/avaliacoes")
public class AvaliacaoController {
 private final AvaliacaoService service;
 public AvaliacaoController(AvaliacaoService s){service=s;}
 @GetMapping public List<AvaliacaoResponseDTO> listar(){return service.listar();}
 @GetMapping("/{id}") public AvaliacaoResponseDTO buscar(@PathVariable Long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<AvaliacaoResponseDTO> criar(@Valid @RequestBody AvaliacaoRequestDTO d){return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(d));}
 @PutMapping("/{id}") public AvaliacaoResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody AvaliacaoRequestDTO d){return service.atualizar(id,d);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id){service.excluir(id);return ResponseEntity.noContent().build();}
}
