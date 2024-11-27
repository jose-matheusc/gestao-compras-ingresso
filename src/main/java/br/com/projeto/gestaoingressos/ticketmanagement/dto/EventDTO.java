package br.com.projeto.gestaoingressos.ticketmanagement.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String location;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NonNull
    private BigDecimal ticketPrice;

    @NonNull
    private String description;

}
