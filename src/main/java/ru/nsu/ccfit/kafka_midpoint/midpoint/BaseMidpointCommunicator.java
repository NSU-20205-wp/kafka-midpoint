package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class BaseMidpointCommunicator {

    private final MidpointConfiguration midpointConfiguration = new MidpointConfiguration();
    protected String baseUrl;
    protected String operationType;
    protected String endpoint;
    protected String typeObject;
    protected HttpURLConnection connection;

    protected BaseMidpointCommunicator() {
        baseUrl = "http://" + midpointConfiguration.getMidpointHost() + ":" + midpointConfiguration.getMidpointPort() +
                "/midpoint/ws/rest";
    }

    protected void openConnection() throws IOException {
        URL url = new URL(endpoint);
        connection = (HttpURLConnection) url.openConnection();

        String authorizationHeader = "Basic " + java.util.Base64.getEncoder().encodeToString(("administrator:5ecr3t")
                .getBytes()); // Заголовок авторизации
        connection.setRequestProperty("Authorization", authorizationHeader);
        connection.setRequestMethod(operationType);

        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");

        connection.setInstanceFollowRedirects(false);
        connection.setConnectTimeout(500);

    }

    protected int sendJsonRequest(String jsonRequest) throws IOException {

        connection.connect();
        byte[] jsonBytes = jsonRequest.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(jsonBytes);
        return connection.getResponseCode();
    }

    public abstract Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException;

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }
}
