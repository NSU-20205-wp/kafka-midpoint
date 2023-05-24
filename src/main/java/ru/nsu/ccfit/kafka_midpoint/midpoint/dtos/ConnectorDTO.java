package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectorDTO extends MidpointDTO{

    private String displayName;
    private String description;

}
