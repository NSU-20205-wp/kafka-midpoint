package ru.nsu.ccfit.kafka_midpoint.midpoint_DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

public class JSONUtils {
    public static JSONObject wrapper(JSONObject roleJson, String objectType) throws JsonProcessingException {
        JSONObject root = new JSONObject();
        root.put(objectType, roleJson);
        return root;
    }
}
