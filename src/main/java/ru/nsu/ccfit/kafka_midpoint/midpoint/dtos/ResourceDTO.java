package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ResourceDTO extends MidpointDTO {


    private String description;
    private ConnectorRefDTO connectorRef;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ConnectorRefDTO {
        private String oid;
        private String type = "ConnectorType";

        public ConnectorRefDTO(String oid) {
            this.oid = oid;
        }
    }

}
