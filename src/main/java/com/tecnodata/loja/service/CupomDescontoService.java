package com.tecnodata.loja.service;

import com.tecnodata.loja.entities.CupomDesconto;
import com.tecnodata.loja.exceptions.TecnodataException;
import com.tecnodata.loja.repository.CupomDescontoRepository;
import com.tecnodata.loja.request.CupomDescontoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CupomDescontoService {
    @Autowired
    private CupomDescontoRepository cupomDescontoRepository;

    public CupomDesconto salvarCupomDesconto(CupomDescontoRequest request) {
        try {
            return cupomDescontoRepository.save(CupomDesconto.builder()
                    .codigo(request.getCodigo())
                    .desconto(request.getDesconto())
                            .status(Boolean.TRUE)
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public CupomDesconto buscarCupomDesconto(String codigo) {
        try {
            return cupomDescontoRepository.retornaCupomPeloCodigo(codigo);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public List<CupomDesconto> buscarListaCupomDesconto() {
        try {
            return cupomDescontoRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }

    public CupomDesconto inativarCupom(String codigo) {
        try {
            CupomDesconto cupomDesconto = cupomDescontoRepository.retornaCupomPeloCodigo(codigo);
            cupomDesconto.setStatus(Boolean.FALSE);
            return cupomDescontoRepository.save(cupomDesconto);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TecnodataException(e.getMessage(), e.getCause());
        }
    }
}
