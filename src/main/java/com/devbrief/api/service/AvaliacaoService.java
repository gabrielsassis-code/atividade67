package com.devbrief.api.service;
import com.devbrief.api.entity.*;
import com.devbrief.api.repository.*;
import com.devbrief.api.dto.avaliacao.*;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AvaliacaoService {
 private final AvaliacaoRepository repo; private final ClienteRepository clientes; private final ProdutoRepository produtos;
 public AvaliacaoService(AvaliacaoRepository r,ClienteRepository c,ProdutoRepository p){repo=r;clientes=c;produtos=p;}
 private AvaliacaoResponseDTO dto(Avaliacao a){return new AvaliacaoResponseDTO(a.getId(),a.getCliente().getId(),a.getCliente().getNome(),a.getProduto().getId(),a.getProduto().getNome(),a.getNota(),a.getComentario());}
 private Avaliacao montar(AvaliacaoRequestDTO d,Avaliacao a){a.setCliente(clientes.findById(d.clienteId()).orElseThrow(()->new RecursoNaoEncontradoException("Cliente não encontrado: "+d.clienteId())));a.setProduto(produtos.findById(d.produtoId()).orElseThrow(()->new RecursoNaoEncontradoException("Produto não encontrado: "+d.produtoId())));a.setNota(d.nota());a.setComentario(d.comentario());return a;}
 public List<AvaliacaoResponseDTO> listar(){return repo.findAll().stream().map(this::dto).toList();}
 public AvaliacaoResponseDTO buscar(Long id){return dto(repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Avaliação não encontrada: "+id)));}
 public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO d){return dto(repo.save(montar(d,new Avaliacao())));}
 public AvaliacaoResponseDTO atualizar(Long id,AvaliacaoRequestDTO d){Avaliacao a=repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Avaliação não encontrada: "+id));return dto(repo.save(montar(d,a)));}
 public void excluir(Long id){if(!repo.existsById(id))throw new RecursoNaoEncontradoException("Avaliação não encontrada: "+id);repo.deleteById(id);}
}
