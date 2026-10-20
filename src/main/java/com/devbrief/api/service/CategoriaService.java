package com.devbrief.api.service;
import com.devbrief.api.entity.Categoria;
import com.devbrief.api.repository.CategoriaRepository;
import com.devbrief.api.dto.categoria.CategoriaRequestDTO;
import com.devbrief.api.dto.categoria.CategoriaResponseDTO;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CategoriaService {
 private final CategoriaRepository repo;
 public CategoriaService(CategoriaRepository repo) { this.repo=repo; }
 private CategoriaResponseDTO dto(Categoria e) { return new CategoriaResponseDTO(e.getId(), e.getNome(), e.getDescricao()); }
 public List<CategoriaResponseDTO> listar() { return repo.findAll().stream().map(this::dto).toList(); }
 public CategoriaResponseDTO buscar(Long id) { return dto(repo.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrado: "+id))); }
 public CategoriaResponseDTO criar(CategoriaRequestDTO dto) { Categoria e=new Categoria(); e.setNome(dto.nome());
  e.setDescricao(dto.descricao()); return dto(repo.save(e)); }
 public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) { Categoria e=repo.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrado: "+id)); e.setNome(dto.nome());
  e.setDescricao(dto.descricao()); return dto(repo.save(e)); }
 public void excluir(Long id) { if(!repo.existsById(id)) throw new RecursoNaoEncontradoException("Categoria não encontrado: "+id); repo.deleteById(id); }
}
