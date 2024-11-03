package br.com.projeto.gestaoingressos.ticketmanagement.service;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.EventDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.Event;
import br.com.projeto.gestaoingressos.ticketmanagement.repository.RepositoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServiceEvent {

    private final RepositoryEvent repositoryEvent;

    public void createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setLocation(eventDTO.getLocation());
        event.setStartTime(LocalDateTime.now());
        event.setEndTime(LocalDateTime.now().plusDays(5));
        event.setTicketPrice(eventDTO.getTicketPrice());
        event.setAvailableTickets(eventDTO.getAvailableTickets());

        repositoryEvent.save(event);
    }

}
