package br.com.projeto.gestaoingressos.ticketmanagement.repository;

import br.com.projeto.gestaoingressos.ticketmanagement.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

