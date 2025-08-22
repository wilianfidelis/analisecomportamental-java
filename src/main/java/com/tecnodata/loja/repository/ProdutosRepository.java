package com.tecnodata.loja.repository;

import com.tecnodata.loja.entities.Pedidos;
import com.tecnodata.loja.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

    @Query(value = "SELECT * FROM produtos produtos WHERE produtos.uf = :uf", nativeQuery = true)
    List<Produtos> retornarProdutosPeloUf(@Param("uf") String uf);
}
