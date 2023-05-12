package ru.nsu.ccfit.kafka_midpoint.midpoint;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseMidpointCommunicator {

    private final MidpointConfiguration midpointConfiguration = new MidpointConfiguration();
    protected String baseUrl;
    protected String operationType;
    protected String endpoint;
    protected String typeObject;
    protected HttpURLConnection connection;

    public BaseMidpointCommunicator() {
        baseUrl = "http://" + midpointConfiguration.getMidpointHost() + ":" + midpointConfiguration.getMidpointPort() +
                "/midpoint/ws/rest";
    }

    void openConnection() throws IOException {
        URL url = new URL(endpoint);
        connection = (HttpURLConnection) url.openConnection();

        String authorizationHeader = "Basic " + java.util.Base64.getEncoder().encodeToString(("administrator:5ecr3t")
                .getBytes()); // Заголовок авторизации
        connection.setRequestProperty("Authorization", authorizationHeader);
        connection.setRequestMethod(operationType);

        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");

        connection.setInstanceFollowRedirects(false);
        connection.setConnectTimeout(200);

    }
}
