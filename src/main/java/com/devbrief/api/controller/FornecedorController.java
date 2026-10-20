package com.devbrief.api.controller;
import com.devbrief.api.service.FornecedorService;
import com.devbrief.api.dto.fornecedor.FornecedorRequestDTO;
import com.devbrief.api.dto.fornecedor.FornecedorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
 private final FornecedorService service;
 public FornecedorController(FornecedorService service) { this.service=service; }
 @GetMapping public List<FornecedorResponseDTO> listar() { return service.listar(); }
 @GetMapping("/<built-in function id>") public FornecedorResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }
 @PostMapping public ResponseEntity<FornecedorResponseDTO> criar(@Valid @RequestBody FornecedorRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto)); }
 @PutMapping("/<built-in function id>") public FornecedorResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody FornecedorRequestDTO dto) { return service.atualizar(id,dto); }
 @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> excluir(@PathVariable Long id) { service.excluir(id); return ResponseEntity.noContent().build(); }
}
