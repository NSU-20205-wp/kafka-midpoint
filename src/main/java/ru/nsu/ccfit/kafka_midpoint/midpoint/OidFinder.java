package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ConnectorDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ResourceDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ConnectorSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ResourceSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.RoleSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OidFinder {

    private OidFinder() {}

    public static String findUserOid(String field, String value) throws IOException {
        UserSearcher userSearcher = new UserSearcher();
        ArrayList<UserDTO> listUsers = new ArrayList<>(userSearcher.getListObjects(field, value));
        if (listUsers.isEmpty()) {
            return null;
        }
        return listUsers.get(0).getOid();
    }

    public static String findRoleOid(String field, String value) throws IOException {
        RoleSearcher roleSearcher = new RoleSearcher();
        ArrayList<RoleDTO> listRoles = new ArrayList<>(roleSearcher.getListObjects(field, value));
        if (listRoles.isEmpty()) {
            return null;
        }
        return listRoles.get(0).getOid();
    }
    public static String findConnectorOid(String field, String value) throws IOException {
        ConnectorSearcher connectorSearcher = new ConnectorSearcher();
        List<ConnectorDTO> listConnectors = connectorSearcher.getListObjects(field, value);
        if (listConnectors.isEmpty()) {
            return null;
        }
        return listConnectors.get(0).getOid();
    }

    public static String findResourceOid(String field, String value) throws IOException {
        ResourceSearcher resourceSearcher = new ResourceSearcher();
        List<ResourceDTO> listResources = resourceSearcher.getListObjects(field, value);
        if (listResources.isEmpty()) {
            return null;
        }
        return listResources.get(0).getOid();
    }

    public static String findOid(String typeObject, String field, String value) throws IOException {
        switch (typeObject) {
            case("role") -> {
                return OidFinder.findRoleOid(field, value);
            }
            case("user") -> {
                return OidFinder.findUserOid(field, value);
            }
            case("resource") -> {
                return OidFinder.findResourceOid(field, value);
            }
            case("connector") -> {
                return OidFinder.findConnectorOid(field, value);
            }
            default -> {
                return null;
            }
        }
    }

}

