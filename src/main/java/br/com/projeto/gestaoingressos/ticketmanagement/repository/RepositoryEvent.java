package br.com.projeto.gestaoingressos.ticketmanagement.repository;


import br.com.projeto.gestaoingressos.ticketmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEvent extends JpaRepository<Event, Long> {
}
