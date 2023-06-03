package ru.nsu.ccfit.kafka_midpoint.midpoint.creators;

import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.deleters.UserDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.nsu.ccfit.kafka_midpoint.utilities.StringUtilities.generateString;

class UserCreatorTest {
    @Test
    void testSendRequest() throws IOException, ObjectNotFoundException, ProductCreatorException {
        String name = generateString(10);
        MidpointCreator creator = new UserCreator();
        Map<String, Object> createParams = new HashMap<>();
        createParams.put("name", name);
        creator.doOperation(createParams);
        int responseCode = creator.getResponseCode();
        assertEquals(2, responseCode / 100);

        MidpointSearcher searcher = new UserSearcher();
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("fieldName", "name");
        searchParams.put("value", name);
        ArrayList<UserDTO> response = (ArrayList<UserDTO>) searcher.doOperation(searchParams);
        responseCode = searcher.getResponseCode();
        assertEquals(2, responseCode / 100);
        assertEquals(createParams.get("name"), response.get(0).getName());

        MidpointDeleter deleter = new UserDeleter();
        deleter.doOperation(createParams);
        responseCode = deleter.getResponseCode();
        assertEquals(2, responseCode / 100);
    }

    @Test
    void testDoubleCreateRequest() throws IOException, ProductCreatorException {
        String name = generateString(10);
        MidpointCreator creator = new UserCreator();
        Map<String, Object> createParams = new HashMap<>();
        createParams.put("name", name);
        creator.doOperation(createParams);
        int responseCode = creator.getResponseCode();
        assertEquals(2, responseCode / 100);

        MidpointCreator creator2 = new UserCreator();
        creator2.doOperation(createParams);
        responseCode = creator2.getResponseCode();
        assertEquals(4, responseCode / 100);

        MidpointSearcher searcher = new UserSearcher();
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("fieldName", "name");
        searchParams.put("value", name);
        ArrayList<UserDTO> response = (ArrayList<UserDTO>) searcher.doOperation(searchParams);
        responseCode = searcher.getResponseCode();
        assertEquals(2, responseCode / 100);
        assertEquals(createParams.get("name"), response.get(0).getName());

        MidpointDeleter deleter = new UserDeleter();
        deleter.doOperation(createParams);
        responseCode = deleter.getResponseCode();
        assertEquals(2, responseCode / 100);
    }

    @Test
    void testNormalCreateRequestWithEmptyParams() throws IOException, ProductCreatorException {
        MidpointCreator creator = new UserCreator();
        Map<String, Object> createParams = new HashMap<>();
        creator.doOperation(createParams);
        int responseCode = creator.getResponseCode();
        assertEquals(5, responseCode / 100);
    }

    @Test
    void testNormalCreateRequestWithNullParams() throws IOException {
        MidpointCreator creator = new UserCreator();
        assertThrows(NullPointerException.class, () -> {
            creator.doOperation(null);
        });
    }

    @Test
    void testNormalCreateRequestWithWrongParams() throws IOException {
        String name = generateString(10);
        MidpointCreator creator = new UserCreator();
        Map<String, Object> createParams = new HashMap<>();
        createParams.put("", name);
        assertThrows(ProductCreatorException.class, () -> {
            creator.doOperation(createParams);
        });
    }
}