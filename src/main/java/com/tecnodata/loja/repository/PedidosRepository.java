package com.tecnodata.loja.repository;

import com.tecnodata.loja.entities.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {
    @Query(value = "SELECT obj.id FROM pedidos obj ORDER BY obj.id DESC LIMIT 1", nativeQuery = true)
    Long retornaUltimoPedidoId();

    @Query(value = "SELECT * FROM pedidos pedidos WHERE pedidos.cnpj = :cnpj ORDER BY pedidos.id DESC", nativeQuery = true)
    List<Pedidos> retornaPedidosPeloCnpj(@Param("cnpj") String cnpj);

    @Query(value = "SELECT * FROM pedidos pedidos WHERE pedidos.transaction_id = :transactionId", nativeQuery = true)
    Pedidos retornaPedidosPeloTransactionId(@Param("transactionId") String transactionId);
}
