package br.com.projeto.gestaoingressos.ticketmanagement.repository;

import br.com.projeto.gestaoingressos.ticketmanagement.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
}
