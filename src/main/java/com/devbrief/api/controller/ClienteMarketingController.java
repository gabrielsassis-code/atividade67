package com.devbrief.api.controller;
import com.devbrief.api.repository.ClienteRepository;
import com.devbrief.api.dto.cliente.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class ClienteMarketingController {
 private final ClienteRepository repo;
 public ClienteMarketingController(ClienteRepository repo) { this.repo=repo; }
 @GetMapping("/clientes-mail-marketing")
 public List<ClienteMarketingDTO> email() { return repo.findAll().stream().map(c -> new ClienteMarketingDTO(c.getNome(), c.getEmail())).toList(); }
 @GetMapping("/clientes-whatsapp-marketing")
 public List<ClienteWhatsappDTO> whatsapp() { return repo.findAll().stream().map(c -> new ClienteWhatsappDTO(c.getNome(), c.getTelefone())).toList(); }
}
