package com.devbrief.api.repository;
import com.devbrief.api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
