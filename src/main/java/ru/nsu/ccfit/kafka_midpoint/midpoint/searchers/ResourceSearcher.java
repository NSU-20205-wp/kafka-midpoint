package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ResourceDTO;

import java.io.IOException;
import java.util.ArrayList;

public class ResourceSearcher extends MidpointSearcher {

    public ResourceSearcher() throws IOException {
        super("resource");
    }

    public ArrayList<ResourceDTO> getListObjects() {
        return super.getListObjects(new TypeReference<>() {
        });
    }
}

