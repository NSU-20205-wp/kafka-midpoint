package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSearcher extends MidpointSearcher {

    public UserSearcher() throws IOException {
        super("user");
    }

    public List<UserDTO> getListObjects() {
        return super.getListObjects(new TypeReference<>() {
        });
    }
}
