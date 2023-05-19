package ru.nsu.ccfit.kafka_midpoint.midpoint.searchers;

import com.fasterxml.jackson.core.type.TypeReference;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public class UserSearcher extends MidpointSearcher {

    public UserSearcher() throws IOException {
        super("user");
    }

    public ArrayList<UserDTO> getListObjects() {
        return super.getListObjects(new TypeReference<>() {
        });
    }
}
