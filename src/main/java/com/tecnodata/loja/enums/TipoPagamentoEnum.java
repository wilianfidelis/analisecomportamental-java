package com.tecnodata.loja.enums;

public enum TipoPagamentoEnum {

    BOLETO("boleto"),
    PIX("pix"),
    CARTAO("cartao");

    private String descricao;
    TipoPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
