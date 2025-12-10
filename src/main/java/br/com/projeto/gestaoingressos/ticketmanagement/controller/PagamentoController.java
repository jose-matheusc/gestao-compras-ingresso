package br.com.projeto.gestaoingressos.ticketmanagement.controller;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.PagamentoDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamento")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> pagar(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO processado = pagamentoService.processarPagamento(pagamentoDTO);
        return ResponseEntity.ok(processado);
    }
}

