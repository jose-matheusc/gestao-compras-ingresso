package br.com.projeto.gestaoingressos.ticketmanagement.service;

import br.com.projeto.gestaoingressos.ticketmanagement.dto.EventDTO;
import br.com.projeto.gestaoingressos.ticketmanagement.entity.EventModel;
import br.com.projeto.gestaoingressos.ticketmanagement.repository.RepositoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceEvent {

    private final RepositoryEvent repositoryEvent;

    public void createEvent(EventDTO eventDTO) {
        EventModel eventModel = mapperDtoToEntity(eventDTO);
        eventModel.setStartTime(LocalDateTime.now());
        eventModel.setEndTime(LocalDateTime.now().plusDays(8));

        repositoryEvent.save(eventModel);
    }

    public EventModel updateEvent(Long id, EventDTO eventDTO) {

        EventModel eventModel = repositoryEvent.findById((id)).orElseThrow();
        EventModel newEventModel = updateDtoToEntity(eventDTO, eventModel);


        return repositoryEvent.save(newEventModel);
    }

    private EventModel updateDtoToEntity(EventDTO eventDTO, EventModel eventModel) {
        eventModel.setName(eventDTO.getName());
        eventModel.setDescription(eventDTO.getDescription());
        eventModel.setLocation(eventDTO.getLocation());
        eventModel.setStartTime(eventDTO.getStartTime());
        eventModel.setEndTime(eventDTO.getEndTime());
        eventModel.setTicketPrice(eventDTO.getTicketPrice());
        return eventModel;
    }

    private EventDTO mapperEntityToDto(EventModel eventModel) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventDTO.getId());
        eventDTO.setLocation(eventModel.getLocation());
        eventDTO.setStartTime(LocalDateTime.now());
        eventDTO.setEndTime(LocalDateTime.now().plusDays(8));
        eventDTO.setTicketPrice(eventModel.getTicketPrice());
        eventDTO.setDescription(eventModel.getDescription());
        return eventDTO;
    }


    private EventModel mapperDtoToEntity(EventDTO eventDTO) {
        EventModel eventModel = new EventModel();
        eventModel.setName(eventDTO.getName());
        eventModel.setLocation(eventDTO.getLocation());
        eventModel.setStartTime(eventDTO.getStartTime());
        eventModel.setEndTime(eventDTO.getEndTime());
        eventModel.setTicketPrice(eventDTO.getTicketPrice());
        eventModel.setDescription(eventDTO.getDescription());
        return eventModel;
    }

    public List<EventModel> getAllEvents() {
        return repositoryEvent.getAlltEvents();
    }

    public void deleteEvent(Long id) {
        repositoryEvent.deleteById(id);
    }
}
