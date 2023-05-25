package ru.nsu.ccfit.kafka_midpoint.midpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.MidpointDTO;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.processing.factory.creator.ProductCreatorException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

public class MidpointCreator extends BaseMidpointCommunicator {
    private static final Logger logger = Logger.getLogger(MidpointCreator.class.getName());
    private final ObjectMapper mapper = new ObjectMapper();

    public MidpointCreator(String typeObject) throws IOException {
        super();
        this.typeObject = typeObject;
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + 's';
        logger.info(() -> typeObject + ":\n base url: " + baseUrl + "\n endpoint: " + endpoint);
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
    }

    private int sendRequest(Object dto) throws IOException {

        connection.connect();
        byte[] jsonBytes = JSONUtils.wrapper(new JSONObject(dto), typeObject).
                toString().getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(jsonBytes);

        return connection.getResponseCode();
    }


    @Override
    public Object doOperation(Map<String, Object> params) throws IOException, ProductCreatorException {
        MidpointDTO dto = (MidpointDTO) AbstractFactory.instance().getFactory("dto").createProduct(
                typeObject, new String[]{mapper.writeValueAsString(params)});
        logger.info(() -> "created dto with name: " + dto.getName());
        return sendRequest(dto);
    }
}
