package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.nsu.ccfit.kafka_midpoint.midpoint.ModificationType;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.TargetRefDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers.UserModifier;

import java.io.IOException;

public class UserAssigner {

    UserModifier modifier;

    public UserAssigner(String nameUser) throws ObjectNotFoundException, IOException {
        modifier = new UserModifier(nameUser);

    }

    private ObjectNode builtValueForRole(String roleName) throws ObjectNotFoundException, IOException {
        TargetRefDTO targetRefDTO = new TargetRefDTO(OidFinder.findRoleOid("name", roleName), "RoleType");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode targetRef = mapper.createObjectNode();
        targetRef.set("targetRef", mapper.valueToTree(targetRefDTO));
        return targetRef;
    }

    public int assingRole(String roleName) throws ObjectNotFoundException, IOException {
        return modifier.mutateField("assignment", builtValueForRole(roleName), ModificationType.ADD);
    }

    public int revokeRole(String roleName) throws ObjectNotFoundException, IOException {
        return modifier.mutateField("assignment", builtValueForRole(roleName), ModificationType.DELETE);
    }

        String roleOid = findRoleOid(roleName);
        return sendRequest(buildJsonForRole(roleOid));
    }


    public int sendRequest(String jsonRequest) throws IOException {

        connection.connect();
        byte[] jsonBytes = jsonRequest.getBytes(StandardCharsets.UTF_8);
        connection.getOutputStream().write(jsonBytes);
        return connection.getResponseCode();
    }


}
