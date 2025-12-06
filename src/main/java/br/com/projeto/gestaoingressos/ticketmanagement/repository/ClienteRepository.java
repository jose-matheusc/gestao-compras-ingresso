package br.com.projeto.gestaoingressos.ticketmanagement.repository;

import br.com.projeto.gestaoingressos.ticketmanagement.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

