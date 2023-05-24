package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ResourceDTO;

import java.io.IOException;
import java.util.List;

public class ResourceSearcher extends MidpointSearcher {

    public ResourceSearcher() throws IOException {
        super("resource");
    }

    public List<ResourceDTO> getListObjects(String nameField, String value) throws IOException  {
        return super.getListObjects(new TypeReference<>() {
        }, nameField, value);
    }
}

