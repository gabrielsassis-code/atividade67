package com.devbrief.api.service;
import com.devbrief.api.entity.Cliente;
import com.devbrief.api.repository.ClienteRepository;
import com.devbrief.api.dto.cliente.ClienteRequestDTO;
import com.devbrief.api.dto.cliente.ClienteResponseDTO;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClienteService {
 private final ClienteRepository repo;
 public ClienteService(ClienteRepository repo) { this.repo=repo; }
 private ClienteResponseDTO dto(Cliente e) { return new ClienteResponseDTO(e.getId(), e.getNome(), e.getEmail(), e.getTelefone()); }
 public List<ClienteResponseDTO> listar() { return repo.findAll().stream().map(this::dto).toList(); }
 public ClienteResponseDTO buscar(Long id) { return dto(repo.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: "+id))); }
 public ClienteResponseDTO criar(ClienteRequestDTO dto) { Cliente e=new Cliente(); e.setNome(dto.nome());
  e.setEmail(dto.email());
  e.setTelefone(dto.telefone()); return dto(repo.save(e)); }
 public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) { Cliente e=repo.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: "+id)); e.setNome(dto.nome());
  e.setEmail(dto.email());
  e.setTelefone(dto.telefone()); return dto(repo.save(e)); }
 public void excluir(Long id) { if(!repo.existsById(id)) throw new RecursoNaoEncontradoException("Cliente não encontrado: "+id); repo.deleteById(id); }
}
