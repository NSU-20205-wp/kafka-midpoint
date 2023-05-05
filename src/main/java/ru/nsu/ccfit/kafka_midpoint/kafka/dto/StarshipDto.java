package ru.nsu.ccfit.kafka_midpoint.kafka.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StarshipDto extends AbstractDto {

    private String name;
    private String model;

    public StarshipDto(String name) {
        this.name = name;
    }
}
