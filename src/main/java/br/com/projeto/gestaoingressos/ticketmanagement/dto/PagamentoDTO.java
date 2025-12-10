package br.com.projeto.gestaoingressos.ticketmanagement.dto;

import java.math.BigDecimal;

public class PagamentoDTO {

    private String metodo;
    private String dados;
    private BigDecimal valorTotal;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;

    }
    }