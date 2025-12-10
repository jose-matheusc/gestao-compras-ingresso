package br.com.projeto.gestaoingressos.ticketmanagement.service;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.ItemCarrinhoDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.ItemCarrinho;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.Produto;
import br.com.projeto.gestaoingressos.ticketmanagement.repository.ItemCarrinhoRepository;
import br.com.projeto.gestaoingressos.ticketmanagement.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(ItemCarrinhoRepository itemCarrinhoRepository, ProdutoRepository produtoRepository) {
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public ItemCarrinhoDTO adicionar(ItemCarrinhoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(dto.getQuantidade());

        ItemCarrinho salvo = itemCarrinhoRepository.save(item);
        return toDTO(salvo);
    }

    public List<ItemCarrinhoDTO> listar() {
        return itemCarrinhoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void remover(Long id) {
        itemCarrinhoRepository.deleteById(id);
    }

    public void limpar() {
        itemCarrinhoRepository.deleteAll();
    }

    public BigDecimal calcularTotal() {
        return itemCarrinhoRepository.findAll()
                .stream()
                .map(item -> item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private ItemCarrinhoDTO toDTO(ItemCarrinho entity) {
        ItemCarrinhoDTO dto = new ItemCarrinhoDTO();
        dto.setId(entity.getId());
        dto.setProdutoId(entity.getProduto().getId());
        dto.setNomeProduto(entity.getProduto().getNome());
        dto.setPrecoUnitario(entity.getProduto().getPreco());
        dto.setQuantidade(entity.getQuantidade());
        return dto;
    }
}
