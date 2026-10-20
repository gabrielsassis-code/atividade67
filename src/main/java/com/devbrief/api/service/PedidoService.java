package com.devbrief.api.service;
import com.devbrief.api.entity.*;
import com.devbrief.api.repository.*;
import com.devbrief.api.dto.pedido.*;
import com.devbrief.api.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class PedidoService {
 private final PedidoRepository repo; private final ClienteRepository clientes; private final ProdutoRepository produtos;
 public PedidoService(PedidoRepository r,ClienteRepository c,ProdutoRepository p){repo=r;clientes=c;produtos=p;}
 private PedidoResponseDTO dto(Pedido p){
  List<PedidoResponseDTO.Item> itens=p.getItens().stream().map(i->new PedidoResponseDTO.Item(i.getId(),i.getProduto().getId(),i.getProduto().getNome(),i.getQuantidade(),i.getPrecoUnitario(),i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))).toList();
  BigDecimal total=itens.stream().map(PedidoResponseDTO.Item::subtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
  return new PedidoResponseDTO(p.getId(),p.getData(),p.getStatus().name(),p.getCliente().getId(),p.getCliente().getNome(),total,itens);
 }
 private Pedido montar(PedidoRequestDTO d,Pedido p){
  p.setCliente(clientes.findById(d.clienteId()).orElseThrow(()->new RecursoNaoEncontradoException("Cliente não encontrado: "+d.clienteId())));
  if(d.status()!=null&&!d.status().isBlank()){try{p.setStatus(Pedido.StatusPedido.valueOf(d.status().toUpperCase()));}catch(Exception e){throw new IllegalArgumentException("Status inválido. Use PENDENTE, PAGO, ENVIADO, ENTREGUE ou CANCELADO.");}}
  if(p.getData()==null)p.setData(LocalDateTime.now());
  p.getItens().clear();
  for(PedidoRequestDTO.Item item:d.itens()){
   Produto produto=produtos.findById(item.produtoId()).orElseThrow(()->new RecursoNaoEncontradoException("Produto não encontrado: "+item.produtoId()));
   if(produto.getQuantidade()<item.quantidade())throw new IllegalArgumentException("Estoque insuficiente para o produto "+produto.getNome());
   ItemPedido ip=new ItemPedido();ip.setPedido(p);ip.setProduto(produto);ip.setQuantidade(item.quantidade());ip.setPrecoUnitario(produto.getPreco());p.getItens().add(ip);
  }
  return p;
 }
 public List<PedidoResponseDTO> listar(){return repo.findAll().stream().map(this::dto).toList();}
 public PedidoResponseDTO buscar(Long id){return dto(repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Pedido não encontrado: "+id)));}
 @Transactional public PedidoResponseDTO criar(PedidoRequestDTO d){Pedido p=montar(d,new Pedido());Pedido salvo=repo.save(p);return dto(salvo);}
 @Transactional public PedidoResponseDTO atualizar(Long id,PedidoRequestDTO d){Pedido p=repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Pedido não encontrado: "+id));montar(d,p);return dto(repo.save(p));}
 public void excluir(Long id){if(!repo.existsById(id))throw new RecursoNaoEncontradoException("Pedido não encontrado: "+id);repo.deleteById(id);}
}
