package com.tecnodata.loja.repository;

import com.tecnodata.loja.entities.Pedidos;
import com.tecnodata.loja.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutenticacaoRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT COUNT(1) FROM usuario usuario WHERE usuario.email = :email", nativeQuery = true)
    Integer recuperaQtdeEmailCadastrado(@Param("email") String email);

    @Query(value = "SELECT senha FROM usuario usuario WHERE usuario.email = :email", nativeQuery = true)
    String recuperaSenhaUsuarioPorLogin(@Param("email") String email);

    @Query(value = "SELECT * FROM usuario usuario WHERE usuario.email = :email", nativeQuery = true)
    Usuario buscarUsuarioEmail(@Param("email") String email);
}
