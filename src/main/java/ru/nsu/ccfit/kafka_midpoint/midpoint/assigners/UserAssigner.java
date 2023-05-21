package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

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

    private ObjectNode builtValueForResource(String resourceName) throws ObjectNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        ObjectNode constructionNode = objectMapper.createObjectNode();
        ObjectNode resourceRefNode = objectMapper.createObjectNode();
        resourceRefNode.put("oid", OidFinder.findResourceOid("name", resourceName));
        constructionNode.set("resourceRef", resourceRefNode);
        root.set("construction", constructionNode);
        return root;
    }

    public int assignResource(String resourceName) throws ObjectNotFoundException, IOException {
        return modifier.mutateField("assignment", builtValueForResource(resourceName), ModificationType.ADD);
    }

    public int revokeResource(String resourceName) throws ObjectNotFoundException, IOException {
        return modifier.mutateField("assignment", builtValueForResource(resourceName), ModificationType.DELETE);
    }


}
