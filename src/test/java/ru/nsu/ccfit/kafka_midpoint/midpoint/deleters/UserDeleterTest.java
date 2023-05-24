package ru.nsu.ccfit.kafka_midpoint.midpoint.deleters;

import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.creators.UserCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.ccfit.kafka_midpoint.utilities.StringUtilities.generateString;

class UserDeleterTest {
    @Test
    void delete() throws IOException, ObjectNotFoundException {
        String name = generateString(10);
        MidpointDTO dto = new UserDTO(name, "GivenName", "FamilyName", "EmailAddress");
        MidpointCreator creator = new UserCreator();
        int responseCode = creator.sendRequest(dto);
        assertEquals(2, responseCode / 100);

        MidpointSearcher searcher = new UserSearcher();
        searcher.sendSearchRequestForOneField("name", name);
        responseCode = searcher.getResponseCode();
        assertEquals(2, responseCode / 100);

        MidpointDeleter deleter = new UserDeleter(name);
        responseCode = deleter.delete();
        assertEquals(2, responseCode / 100);

        searcher = new UserSearcher();
        searcher.sendSearchRequestForOneField("name", name);
        responseCode = searcher.getResponseCode();
        assertEquals(4, responseCode / 100);
    }
}