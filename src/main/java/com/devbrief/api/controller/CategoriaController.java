package com.devbrief.api.controller;
import com.devbrief.api.service.CategoriaService;
import com.devbrief.api.dto.categoria.CategoriaRequestDTO;
import com.devbrief.api.dto.categoria.CategoriaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
 private final CategoriaService service;
 public CategoriaController(CategoriaService service) { this.service=service; }
 @GetMapping public List<CategoriaResponseDTO> listar() { return service.listar(); }
 @GetMapping("/<built-in function id>") public CategoriaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }
 @PostMapping public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto)); }
 @PutMapping("/<built-in function id>") public CategoriaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto) { return service.atualizar(id,dto); }
 @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> excluir(@PathVariable Long id) { service.excluir(id); return ResponseEntity.noContent().build(); }
}
