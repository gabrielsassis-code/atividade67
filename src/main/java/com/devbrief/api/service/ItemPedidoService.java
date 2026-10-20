package com.devbrief.api.service;
import com.devbrief.api.entity.*;
import com.devbrief.api.repository.*;
import com.devbrief.api.dto.itempedido.*;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ItemPedidoService {
 private final ItemPedidoRepository repo; private final PedidoRepository pedidos; private final ProdutoRepository produtos;
 public ItemPedidoService(ItemPedidoRepository r,PedidoRepository pe,ProdutoRepository pr){repo=r;pedidos=pe;produtos=pr;}
 private ItemPedidoResponseDTO dto(ItemPedido i){return new ItemPedidoResponseDTO(i.getId(),i.getPedido().getId(),i.getProduto().getId(),i.getProduto().getNome(),i.getQuantidade(),i.getPrecoUnitario());}
 private ItemPedido montar(ItemPedidoRequestDTO d,ItemPedido i){i.setPedido(pedidos.findById(d.pedidoId()).orElseThrow(()->new RecursoNaoEncontradoException("Pedido não encontrado")));i.setProduto(produtos.findById(d.produtoId()).orElseThrow(()->new RecursoNaoEncontradoException("Produto não encontrado")));i.setQuantidade(d.quantidade());i.setPrecoUnitario(d.precoUnitario());return i;}
 public List<ItemPedidoResponseDTO> listar(){return repo.findAll().stream().map(this::dto).toList();}
 public ItemPedidoResponseDTO buscar(Long id){return dto(repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("ItemPedido não encontrado: "+id)));}
 public ItemPedidoResponseDTO criar(ItemPedidoRequestDTO d){return dto(repo.save(montar(d,new ItemPedido())));}
 public ItemPedidoResponseDTO atualizar(Long id,ItemPedidoRequestDTO d){ItemPedido i=repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("ItemPedido não encontrado: "+id));return dto(repo.save(montar(d,i)));}
 public void excluir(Long id){if(!repo.existsById(id))throw new RecursoNaoEncontradoException("ItemPedido não encontrado: "+id);repo.deleteById(id);}
}
