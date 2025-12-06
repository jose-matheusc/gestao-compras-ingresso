package br.com.projeto.gestaoingressos.ticketmanagement.service;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.ProdutoDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.Produto;
import br.com.projeto.gestaoingressos.ticketmanagement.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO criar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        Produto salvo = produtoRepository.save(produto);
        return toDTO(salvo);
    }

    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProdutoDTO buscar(Long id) {
        return produtoRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(dto.getNome());
                    produto.setPreco(dto.getPreco());
                    produto.setDescricao(dto.getDescricao());
                    return toDTO(produtoRepository.save(produto));
                })
                .orElse(null);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO toDTO(Produto entity) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }
}

