package br.com.projeto.gestaoingressos.ticketmanagement.controller;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.ItemCarrinhoDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrinhoDTO>> listar() {
        return ResponseEntity.ok(carrinhoService.listar());
    }

    @PostMapping
    public ResponseEntity<ItemCarrinhoDTO> adicionar(@RequestBody ItemCarrinhoDTO itemDTO) {
        ItemCarrinhoDTO criado = carrinhoService.adicionar(itemDTO);
        return ResponseEntity.ok(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        carrinhoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limpar() {
        carrinhoService.limpar();
        return ResponseEntity.noContent().build();
    }
}
