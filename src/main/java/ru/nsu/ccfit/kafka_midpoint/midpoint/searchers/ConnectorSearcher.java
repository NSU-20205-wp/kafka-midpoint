package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ConnectorDTO;

import java.io.IOException;
import java.util.ArrayList;

public class ConnectorSearcher extends MidpointSearcher {

    public ConnectorSearcher() throws IOException {
        super("connector");
    }

    public ArrayList<ConnectorDTO> getListObjects() {
        return super.getListObjects(new TypeReference<>() {
        });
    }
}

