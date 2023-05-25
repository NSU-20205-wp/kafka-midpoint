package ru.nsu.ccfit.kafka_midpoint.midpoint.creators;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.deleters.UserDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.ccfit.kafka_midpoint.utilities.StringUtilities.generateString;

class UserCreatorTest {

    @Test
    void testSendRequest() throws IOException, ObjectNotFoundException {
        String name = generateString(10);
        MidpointDTO dto = new UserDTO(name, "GivenName", "FamilyName", "EmailAddress");
        MidpointCreator creator = new UserCreator();
        int responseCode = creator.sendRequest(dto);
        assertEquals(2, responseCode / 100);

        MidpointSearcher searcher = new UserSearcher();
        responseCode = searcher.getResponseCode();
        assertEquals(2, responseCode / 100);
        searcher.sendSearchRequestForOneField("name", name);
//        searcher.getListObjects();

        MidpointDeleter deleter = new UserDeleter(name);
        responseCode = deleter.delete();
        assertEquals(2, responseCode / 100);
    }
}