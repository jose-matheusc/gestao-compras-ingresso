package br.com.projeto.gestaoingressos.ticketmanagement.controller;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.EventDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.EventModel;
import br.com.projeto.gestaoingressos.ticketmanagement.service.ServiceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/event/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        serviceEvent.updateEvent(id, eventDTO);
        return ResponseEntity.ok("Event updated successfully");
    }

    @GetMapping("/event/all")
    public ResponseEntity<List<EventModel>> getAllEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceEvent.getAllEvents());
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        serviceEvent.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
