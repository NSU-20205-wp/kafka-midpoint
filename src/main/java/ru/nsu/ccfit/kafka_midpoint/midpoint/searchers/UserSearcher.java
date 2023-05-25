package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;

import java.io.IOException;
import java.util.List;

public class UserSearcher extends MidpointSearcher {

    public UserSearcher() throws IOException {
        super("user");
    }

    public List<UserDTO> getListObjects(String nameField, String value) throws IOException  {
        return super.getListObjects(new TypeReference<>() {
        }, nameField, value);
    }
}
