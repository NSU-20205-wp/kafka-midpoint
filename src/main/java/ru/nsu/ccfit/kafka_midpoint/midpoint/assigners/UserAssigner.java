package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.nsu.ccfit.kafka_midpoint.midpoint.ModificationType;
import ru.nsu.ccfit.kafka_midpoint.midpoint.OidFinder;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.TargetRefDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.CategoryNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.MidpointException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.modifiers.UserModifier;

import java.io.IOException;

public class UserAssigner {

    private UserModifier modifier;

    public UserAssigner(String nameUser) throws ObjectNotFoundException, IOException {
        modifier = new UserModifier(nameUser);

    }

    private ObjectNode buildValueForRole(String roleName) throws ObjectNotFoundException, IOException {
        TargetRefDTO targetRefDTO = new TargetRefDTO(OidFinder.findOid("role", "name", roleName), "RoleType");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode targetRef = mapper.createObjectNode();
        targetRef.set("targetRef", mapper.valueToTree(targetRefDTO));
        return targetRef;
    }

    public int assignRole(String roleName) throws ObjectNotFoundException, IOException {
        return modifier.updateField("assignment", buildValueForRole(roleName), ModificationType.ADD);
    }

    public int revokeRole(String roleName) throws ObjectNotFoundException, IOException {
        return modifier.updateField("assignment", buildValueForRole(roleName), ModificationType.DELETE);
    }

    private ObjectNode buildValueForResource(String resourceName) throws ObjectNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        ObjectNode constructionNode = objectMapper.createObjectNode();
        ObjectNode resourceRefNode = objectMapper.createObjectNode();
        resourceRefNode.put("oid", OidFinder.findOid("resource","name", resourceName));
        constructionNode.set("resourceRef", resourceRefNode);
        root.set("construction", constructionNode);
        return root;
    }

    public int assignResource(String resourceName) throws ObjectNotFoundException, IOException {
        return modifier.updateField("assignment", buildValueForResource(resourceName), ModificationType.ADD);
    }

    public int revokeResource(String resourceName) throws ObjectNotFoundException, IOException {
        return modifier.updateField("assignment", buildValueForResource(resourceName), ModificationType.DELETE);
    }

    public int assign(String what, String name) throws MidpointException, IOException {
        ValueBuilder builder;
        try {
            builder = (ValueBuilder) AbstractFactory.instance().getFactory("build")
                    .createProduct(what, null);
        }
        catch(ProductCreatorException e) {
            throw new CategoryNotFoundException(e);
        }
        return modifier.updateField("assignment", builder.buildValue(name), ModificationType.ADD);
    }

    public int revoke(String what, String name) throws MidpointException, IOException {
        ValueBuilder builder;
        try {
            builder = (ValueBuilder) AbstractFactory.instance().getFactory("build")
                    .createProduct(what, null);
        }
        catch(ProductCreatorException e) {
            throw new CategoryNotFoundException(e);
        }
        return modifier.updateField("assignment", builder.buildValue(name), ModificationType.DELETE);
    }


}
