package com.devbrief.api.controller;
import com.devbrief.api.service.ClienteService;
import com.devbrief.api.dto.cliente.ClienteRequestDTO;
import com.devbrief.api.dto.cliente.ClienteResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/clientes")
public class ClienteController {
 private final ClienteService service;
 public ClienteController(ClienteService service) { this.service=service; }
 @GetMapping public List<ClienteResponseDTO> listar() { return service.listar(); }
 @GetMapping("/<built-in function id>") public ClienteResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }
 @PostMapping public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto)); }
 @PutMapping("/<built-in function id>") public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) { return service.atualizar(id,dto); }
 @DeleteMapping("/<built-in function id>") public ResponseEntity<Void> excluir(@PathVariable Long id) { service.excluir(id); return ResponseEntity.noContent().build(); }
}
