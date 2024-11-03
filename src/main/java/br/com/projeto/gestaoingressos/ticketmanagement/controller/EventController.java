package br.com.projeto.gestaoingressos.ticketmanagement.controller;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.EventDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.service.ServiceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {

    private final ServiceEvent serviceEvent;

    @PostMapping("/event")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        serviceEvent.createEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventDTO);
    }
}
