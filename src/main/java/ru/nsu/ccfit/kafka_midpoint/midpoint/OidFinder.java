package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ConnectorDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ResourceDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ConnectorSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ResourceSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.RoleSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OidFinder {

    public static String findUserOid(String field, String value) throws ObjectNotFoundException, IOException {
        UserSearcher userSearcher = new UserSearcher();
        ArrayList<UserDTO> listUsers = new ArrayList<>(userSearcher.getListObjects(field, value));
        if (listUsers.isEmpty()) {
            throw new ObjectNotFoundException("user with " + field + " '" + value + "' not found");
        }
        return listUsers.get(0).getOid();
    }

    public static String findRoleOid(String field, String value) throws ObjectNotFoundException, IOException {
        RoleSearcher roleSearcher = new RoleSearcher();
        ArrayList<RoleDTO> listRoles = new ArrayList<>(roleSearcher.getListObjects(field, value));
        if (listRoles.isEmpty()) {
            throw new ObjectNotFoundException("role with " + field + " '" + value + "' not found");
        }
        return listRoles.get(0).getOid();
    }
    public static String findConnectorOid(String field, String value) throws ObjectNotFoundException, IOException {
        ConnectorSearcher connectorSearcher = new ConnectorSearcher();
        List<ConnectorDTO> listConnectors = connectorSearcher.getListObjects(field, value);
        if (listConnectors.isEmpty()) {
            throw new ObjectNotFoundException("connector with " + field + " '" + value + "' not found");
        }
        return listConnectors.get(0).getOid();
    }

    public static String findResourceOid(String field, String value) throws ObjectNotFoundException, IOException {
        ResourceSearcher resourceSearcher = new ResourceSearcher();
        List<ResourceDTO> listResources = resourceSearcher.getListObjects(field, value);
        if (listResources.isEmpty()) {
            throw new ObjectNotFoundException("resource with " + field + " '" + value + "' not found");
        }
        return listResources.get(0).getOid();
    }

}

