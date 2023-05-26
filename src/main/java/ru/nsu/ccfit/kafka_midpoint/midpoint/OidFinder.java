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
        userSearcher.sendSearchRequestForOneField(field, value);
        ArrayList<UserDTO> listUsers = userSearcher.getListObjects();
        if (listUsers == null ) {
            throw new ObjectNotFoundException("user with " +field + " '" + value + "' not found");
        }
        return listUsers.get(0).getOid();
    }

    public static String findRoleOid(String field, String value) throws ObjectNotFoundException, IOException {
        RoleSearcher roleSearcher = new RoleSearcher();
        roleSearcher.sendSearchRequestForOneField(field, value);
        ArrayList<RoleDTO> listRoles = roleSearcher.getListObjects();
        if (listRoles == null ) {
            throw new ObjectNotFoundException("role with " +field + " '" + value + "' not found");
        }
        return listRoles.get(0).getOid();
    }
    public static String findConnectorOid(String field, String value) throws ObjectNotFoundException, IOException {
        ConnectorSearcher connectorSearcher = new ConnectorSearcher();
        connectorSearcher.sendSearchRequestForOneField(field, value);
        List<ConnectorDTO> listConnectors = connectorSearcher.getListObjects();
        if (listConnectors == null ) {
            throw new ObjectNotFoundException("connector with " +field + " '" + value + "' not found");
        }
        return listConnectors.get(0).getOid();
    }

    public static String findResourceOid(String field, String value) throws ObjectNotFoundException, IOException {
        ResourceSearcher resourceSearcher = new ResourceSearcher();
        resourceSearcher.sendSearchRequestForOneField(field, value);
        List<ResourceDTO> listResources = resourceSearcher.getListObjects();
        if (listResources == null ) {
            throw new ObjectNotFoundException("resource with " +field + " '" + value + "' not found");
        }
        return listResources.get(0).getOid();
    }

}

