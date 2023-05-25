package ru.nsu.ccfit.kafka_midpoint.midpoint.deleters;

import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointDeleter;
import ru.nsu.ccfit.kafka_midpoint.midpoint.MidpointSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.creators.ResourceCreator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ResourceDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ResourceSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.ccfit.kafka_midpoint.utilities.StringUtilities.generateString;

class ResourceDeleterTest {

    @Test
    void testSendRequest() throws IOException, ProductCreatorException {
        String name = generateString(10);
        MidpointCreator creator = new ResourceCreator();
        Map<String, Object> createParams = new HashMap<>();
        createParams.put("name", name);
        creator.doOperation(createParams);
        int responseCode = creator.getResponseCode();
        assertEquals(2, responseCode / 100);

        MidpointSearcher searcher = new ResourceSearcher();
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("fieldName", "name");
        searchParams.put("value", name);
        ArrayList<ResourceDTO> response = (ArrayList<ResourceDTO>) searcher.doOperation(searchParams);
        responseCode = searcher.getResponseCode();
        assertEquals(2, responseCode / 100);
        assertEquals(createParams.get("name"), response.get(0).getName());

        MidpointDeleter deleter = new ResourceDeleter();
        deleter.doOperation(createParams);
        responseCode = deleter.getResponseCode();
        assertEquals(2, responseCode / 100);

        MidpointSearcher searcher2 = new ResourceSearcher();
        response = (ArrayList<ResourceDTO>) searcher2.doOperation(searchParams);
        responseCode = searcher2.getResponseCode();
        assertEquals(2, responseCode / 100);
        assertTrue(response.isEmpty());
    }
}