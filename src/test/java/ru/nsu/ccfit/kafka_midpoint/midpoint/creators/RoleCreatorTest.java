package ru.nsu.ccfit.kafka_midpoint.midpoint.creators;

import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.deleters.RoleDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.AssignmentDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.RoleSearcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleCreatorTest {
    private String generateString(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    @Test
    void testSendRequest() throws IOException, ObjectNotFoundException {
        String name = generateString(10);
        List<AssignmentDTO> assignmentDTOS = new ArrayList<>();
        MidpointDTO dto = new RoleDTO();
        dto.setName(name);
        MidpointCreator creator = new RoleCreator();
        int responseCode = creator.sendRequest(dto);
        assertEquals(2, responseCode / 100);

        MidpointSearcher searcher = new RoleSearcher();
        searcher.sendSearchRequestForOneField("name", name);
        responseCode = searcher.getResponseCode();
        assertEquals(2, responseCode / 100);

        MidpointDeleter deleter = new RoleDeleter(name);
        responseCode = deleter.delete();
        assertEquals(2, responseCode / 100);
    }
}