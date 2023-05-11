package ru.nsu.ccfit.kafka_midpoint.midpoint_DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RoleCreator {

    static String endpoint = "http://localhost:8080/midpoint/ws/rest/roles";

    JSONObject wrapper(JSONObject roleJson) throws JsonProcessingException {
        JSONObject root = new JSONObject();
        root.put("role", roleJson);
        return root;
    }

    public int sendReq(RoleDTO roleDTO) throws IOException {

        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        String authorizationHeader = "Basic " + java.util.Base64.getEncoder().encodeToString(("administrator:5ecr3t").getBytes()); // Заголовок авторизации
        con.setRequestProperty("Authorization", authorizationHeader);

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setInstanceFollowRedirects(false);
        con.setConnectTimeout(200);
        con.connect();

        byte[] jsonBytes = wrapper(new JSONObject(roleDTO)).toString().getBytes(StandardCharsets.UTF_8);
        con.getOutputStream().write(jsonBytes);

        int responseCode = con.getResponseCode();
        return responseCode;
    }
}
