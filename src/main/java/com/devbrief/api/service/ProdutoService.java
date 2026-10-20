package com.devbrief.api.service;
import com.devbrief.api.entity.*;
import com.devbrief.api.repository.*;
import com.devbrief.api.dto.produto.*;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ProdutoService {
 private final ProdutoRepository repo; private final CategoriaRepository categorias; private final FornecedorRepository fornecedores;
 public ProdutoService(ProdutoRepository r, CategoriaRepository c, FornecedorRepository f) { repo=r; categorias=c; fornecedores=f; }
 private ProdutoResponseDTO dto(Produto p) { return new ProdutoResponseDTO(p.getId(),p.getNome(),p.getQuantidade(),p.getPreco(),p.getCategoria().getId(),p.getCategoria().getNome(),p.getFornecedor()==null?null:p.getFornecedor().getId(),p.getFornecedor()==null?null:p.getFornecedor().getNome()); }
 private Produto montar(ProdutoRequestDTO d, Produto p) {
  p.setNome(d.nome()); p.setQuantidade(d.quantidade()); p.setPreco(d.preco());
  p.setCategoria(categorias.findById(d.categoriaId()).orElseThrow(()->new RecursoNaoEncontradoException("Categoria não encontrada: "+d.categoriaId())));
  p.setFornecedor(d.fornecedorId()==null?null:fornecedores.findById(d.fornecedorId()).orElseThrow(()->new RecursoNaoEncontradoException("Fornecedor não encontrado: "+d.fornecedorId())));
  return p;
 }
 public List<ProdutoResponseDTO> listar(){return repo.findAll().stream().map(this::dto).toList();}
 public ProdutoResponseDTO buscar(Long id){return dto(repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Produto não encontrado: "+id)));}
 public ProdutoResponseDTO criar(ProdutoRequestDTO d){return dto(repo.save(montar(d,new Produto())));}
 public ProdutoResponseDTO atualizar(Long id,ProdutoRequestDTO d){Produto p=repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Produto não encontrado: "+id));return dto(repo.save(montar(d,p)));}
 public void excluir(Long id){if(!repo.existsById(id))throw new RecursoNaoEncontradoException("Produto não encontrado: "+id);repo.deleteById(id);}
}
