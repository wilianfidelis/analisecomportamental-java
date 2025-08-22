package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.Produtos;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.ProdutosRepository;
import com.tecnodata.loja.request.ProdutoRequest;
import com.tecnodata.loja.response.ProdutosResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProdutosService {
    @Autowired
    ProdutosRepository produtosRepository;

    public List<ProdutosResponse> buscarListaProdutos() {
        try {
            return produtosRepository.findAll().stream().map(obj ->
                            ProdutosResponse.builder()
                                    .id(obj.getId())
                                    .nome(obj.getNome())
                                    .descricao(obj.getDescricao())
                                    .imagem(obj.getImagem())
                                    .status(obj.getStatus())
                                    .disponivel(obj.getDisponivel())
                                    .preco(obj.getPreco())
                                    .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public ProdutosResponse buscarProdutoId(String idProduto) {
        try {
            Optional<Produtos> produto = produtosRepository.findById(Integer.valueOf(idProduto));
            if (produto.isPresent()) {
                return ProdutosResponse.builder()
                        .id(produto.get().getId())
                        .nome(produto.get().getNome())
                        .descricao(produto.get().getDescricao())
                        .imagem(produto.get().getImagem())
                        .status(produto.get().getStatus())
                        .disponivel(produto.get().getDisponivel())
                        .preco(produto.get().getPreco())
                        .build();
            }
            return ProdutosResponse.builder().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public Produtos criarProduto(ProdutoRequest request) {
        try {
            return produtosRepository.save(Produtos.builder()
                    .nome(request.getNome())
                    .descricao(request.getDescricao())
                    .quantidade(request.getQuantidade())
                    .preco(request.getPreco())
                    .disponivel(request.getDisponivel())
                    .imagem(request.getImagem())
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }
}
