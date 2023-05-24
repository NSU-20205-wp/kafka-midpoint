package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;

import java.io.IOException;
import java.util.List;

public class RoleSearcher extends MidpointSearcher {

    public RoleSearcher() throws IOException {
        super("role");
    }

    public List<RoleDTO> getListObjects(String nameField, String value) throws IOException  {
        return super.getListObjects(new TypeReference<>() {
        }, nameField, value);
    }
}

