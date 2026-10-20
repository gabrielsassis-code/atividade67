package com.devbrief.api.controller;
import com.devbrief.api.service.PedidoService;
import com.devbrief.api.dto.pedido.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/pedidos")
public class PedidoController {
 private final PedidoService service;
 public PedidoController(PedidoService s){service=s;}
 @GetMapping public List<PedidoResponseDTO> listar(){return service.listar();}
 @GetMapping("/{id}") public PedidoResponseDTO buscar(@PathVariable Long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<PedidoResponseDTO> criar(@Valid @RequestBody PedidoRequestDTO d){return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(d));}
 @PutMapping("/{id}") public PedidoResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody PedidoRequestDTO d){return service.atualizar(id,d);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id){service.excluir(id);return ResponseEntity.noContent().build();}
}
