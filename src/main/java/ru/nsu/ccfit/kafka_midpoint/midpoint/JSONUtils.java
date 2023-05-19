package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;

public class JSONUtils {
    public static JSONObject wrapper(JSONObject roleJson, String objectType) {
        JSONObject root = new JSONObject();
        root.put(objectType, roleJson);
        return root;
    }

    public static String wrapper(String key, String value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set(key, mapper.readTree(value));
        System.out.println(mapper.writeValueAsString(rootNode));
        return mapper.writeValueAsString(rootNode);

    }
}
