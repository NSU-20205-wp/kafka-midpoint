package ru.nsu.ccfit.kafka_midpoint.midpoint.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;

import java.io.IOException;

public class ResourceBuilder implements ValueBuilder {
    @Override
    public ObjectNode buildValue(String name) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        ObjectNode constructionNode = objectMapper.createObjectNode();
        ObjectNode resourceRefNode = objectMapper.createObjectNode();
        resourceRefNode.put("oid", OidFinder.findOid("resource","name", name));
        constructionNode.set("resourceRef", resourceRefNode);
        root.set("construction", constructionNode);
        return root;
    }
}
