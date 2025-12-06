package br.com.projeto.gestaoingressos.ticketmanagement.repository;

import br.com.projeto.gestaoingressos.ticketmanagement.entity.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
}

