package ru.nsu.ccfit.kafka_midpoint.midpoint.assignments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.TargetRefDTO;

import java.io.IOException;

public class RoleAssignment implements Assignment {
    @Override
    public ObjectNode buildValue(String name) throws IOException {
        TargetRefDTO targetRefDTO = new TargetRefDTO(OidFinder.findOid("role", "name", name), "RoleType");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode targetRef = mapper.createObjectNode();
        targetRef.set("targetRef", mapper.valueToTree(targetRefDTO));
        return targetRef;
    }
}
