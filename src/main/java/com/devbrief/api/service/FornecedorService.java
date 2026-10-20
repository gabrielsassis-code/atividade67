package com.devbrief.api.service;
import com.devbrief.api.entity.Fornecedor;
import com.devbrief.api.repository.FornecedorRepository;
import com.devbrief.api.dto.fornecedor.FornecedorRequestDTO;
import com.devbrief.api.dto.fornecedor.FornecedorResponseDTO;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FornecedorService {
 private final FornecedorRepository repo;
 public FornecedorService(FornecedorRepository repo) { this.repo=repo; }
 private FornecedorResponseDTO dto(Fornecedor e) { return new FornecedorResponseDTO(e.getId(), e.getNome(), e.getEmail(), e.getTelefone()); }
 public List<FornecedorResponseDTO> listar() { return repo.findAll().stream().map(this::dto).toList(); }
 public FornecedorResponseDTO buscar(Long id) { return dto(repo.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: "+id))); }
 public FornecedorResponseDTO criar(FornecedorRequestDTO dto) { Fornecedor e=new Fornecedor(); e.setNome(dto.nome());
  e.setEmail(dto.email());
  e.setTelefone(dto.telefone()); return dto(repo.save(e)); }
 public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) { Fornecedor e=repo.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado: "+id)); e.setNome(dto.nome());
  e.setEmail(dto.email());
  e.setTelefone(dto.telefone()); return dto(repo.save(e)); }
 public void excluir(Long id) { if(!repo.existsById(id)) throw new RecursoNaoEncontradoException("Fornecedor não encontrado: "+id); repo.deleteById(id); }
}
