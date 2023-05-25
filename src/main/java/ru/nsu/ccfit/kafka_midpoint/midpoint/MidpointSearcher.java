package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointSearcher extends BaseMidpointCommunicator {
    private static final Logger logger = Logger.getLogger(MidpointSearcher.class.getName());
    protected String jsonResponse;
    protected StringBuilder stringResponse;

    public MidpointSearcher(String typeObject) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + 's' + "/search";
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
    }

    private void sendSearchRequestForOneField(String nameField, String value) throws IOException {

        connection.connect();
        byte[] jsonBytes = JSONUtils.wrapper(JSONUtils.wrapper(
                        JSONUtils.wrapper(new JSONObject().put("path", nameField).put("value", value), "equal"), "filter"), "query").
                toString().getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(jsonBytes);

        // Чтение данных из ответа
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        stringResponse = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            stringResponse.append(inputLine);
        }
        in.close();
    }

    public <T extends MidpointDTO> List<T> getListObjects(String nameField, String value) throws IOException  {
        return new ArrayList<>();
    }

    protected <T extends MidpointDTO> List<T> getListObjects(TypeReference<ArrayList<T>> targetClass,
                                                             String nameField, String value) throws IOException {
        sendSearchRequestForOneField(nameField, value);
        // Обработка полученных данных
        jsonResponse = stringResponse.toString();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONObject(jsonResponse).getJSONObject("object").getJSONArray("object");
        } catch (JSONException exception) {
            logger.warning(exception::getMessage);
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(jsonArray.toString(), targetClass);
        } catch (JsonProcessingException e) {
            logger.warning(e::getMessage);
            return new ArrayList<>();
        }
    }

    @Override
    public Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException {
        String fieldName = (String) params.get("fieldName");
        String value = (String) params.get("value");
        return getListObjects(fieldName, value);
    }
}
