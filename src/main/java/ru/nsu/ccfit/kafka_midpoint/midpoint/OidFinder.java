package ru.nsu.ccfit.kafka_midpoint.midpoint;

import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.exceptions.ObjectNotFoundException;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.RoleSearcher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.util.ArrayList;

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
        System.out.println(listRoles.get(0).getOid());
        return listRoles.get(0).getOid();
    }
}

