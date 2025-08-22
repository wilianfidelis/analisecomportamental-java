package com.tecnodata.loja.repository;

import com.tecnodata.loja.entities.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Integer> {
    @Query(value = "SELECT obj FROM CupomDesconto obj WHERE obj.codigo = :codigo")
    CupomDesconto retornaCupomPeloCodigo(@Param("codigo") String codigo);
}
