package br.com.projeto.gestaoingressos.ticketmanagement.service;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.PagamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final CarrinhoService carrinhoService;

    public PagamentoDTO processarPagamento(PagamentoDTO pagamentoDTO) {
        carrinhoService.limpar();
        return pagamentoDTO;
    }
}
