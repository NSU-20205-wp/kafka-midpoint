package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;

import java.io.IOException;
import java.util.ArrayList;

public class RoleSearcher extends MidpointSearcher {

    public RoleSearcher() throws IOException {
        super("role");
    }

    public ArrayList<RoleDTO> getListObjects() {
        return super.getListObjects(new TypeReference<>() {
        });
    }
}

