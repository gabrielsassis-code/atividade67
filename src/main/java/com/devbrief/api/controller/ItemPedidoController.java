package com.devbrief.api.controller;
import com.devbrief.api.service.ItemPedidoService;
import com.devbrief.api.dto.itempedido.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/itens-pedido")
public class ItemPedidoController {
 private final ItemPedidoService service;
 public ItemPedidoController(ItemPedidoService s){service=s;}
 @GetMapping public List<ItemPedidoResponseDTO> listar(){return service.listar();}
 @GetMapping("/{id}") public ItemPedidoResponseDTO buscar(@PathVariable Long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<ItemPedidoResponseDTO> criar(@Valid @RequestBody ItemPedidoRequestDTO d){return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(d));}
 @PutMapping("/{id}") public ItemPedidoResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody ItemPedidoRequestDTO d){return service.atualizar(id,d);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id){service.excluir(id);return ResponseEntity.noContent().build();}
}
