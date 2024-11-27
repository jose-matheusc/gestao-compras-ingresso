package br.com.projeto.gestaoingressos.ticketmanagement.repository;


import br.com.projeto.gestaoingressos.ticketmanagement.entity.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryEvent extends JpaRepository<EventModel, Long> {

    @Query("SELECT e FROM EventModel e")
    List<EventModel> getAlltEvents();

}
