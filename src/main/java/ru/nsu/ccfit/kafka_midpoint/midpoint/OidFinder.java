package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ConnectorDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ResourceDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.AbstractFactory;
import ru.nsu.ccfit.kafka_midpoint.midpoint.factory.creator.ProductCreatorException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ConnectorSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.ResourceSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.RoleSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OidFinder {
    private static final Logger logger = Logger.getLogger(OidFinder.class.getCanonicalName());

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
        MidpointSearcher searcher;
        try {
            searcher = (MidpointSearcher) AbstractFactory.instance()
                    .getFactory("search").createProduct(typeObject, null);
        }
        catch(ProductCreatorException e) {
            logger.severe(e.getMessage());
            return null;
        }
        if(searcher == null) {
            return null;
        }
        return searcher.getListObjects(field, value).get(0).getOid();
    }

}

