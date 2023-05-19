package ru.nsu.ccfit.kafka_midpoint.midpoint.assigners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.nsu.ccfit.kafka_midpoint.midpoint.BaseMidpointCommunicator;
import ru.nsu.ccfit.kafka_midpoint.midpoint.ModificationType;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.ItemDeltaDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.RoleDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.TargetRefDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.dtos.UserDTO;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.RoleSeacher;
import ru.nsu.ccfit.kafka_midpoint.midpoint.searchers.UserSearcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UserAssigner extends BaseMidpointCommunicator {

    private UserDTO userDTO;
    private String userName;

    private void findUser() throws Exception {
        UserSearcher userSearcher = new UserSearcher();
        userSearcher.sendSearchRequestForOneField("name", userName);
        ArrayList<UserDTO> listUsers = userSearcher.getListObjects();
        if (listUsers.size() != 1) {
            // не найден пользователь с таким именем
            // что делать?
            throw new Exception("user not found");
        }
        userDTO = listUsers.get(0);
    }

    private String findRoleOid(String roleName) throws Exception {
        RoleSeacher roleSeacher = new RoleSeacher();
        roleSeacher.sendSearchRequestForOneField("name", roleName);
        ArrayList<RoleDTO> listRoles = roleSeacher.getListObjects();
        if (listRoles.size() != 1) {
            // не найден пользователь с таким именем
            // что делать?
            throw new Exception("role not found");
        }
        return listRoles.get(0).getOid();
    }


    public UserAssigner(String userName) throws Exception {
        super();
        this.userName = userName;
        findUser();
        this.typeObject = "user";
        operationType = "POST";
        endpoint = baseUrl + '/' + typeObject + "s/" + userDTO.getOid();
        openConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
    }

    private String buildJsonForRole(String roleOid) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        TargetRefDTO targetRefDTO = new TargetRefDTO(roleOid, "RoleType");
        ObjectNode targetRef = mapper.createObjectNode();
        targetRef.set("targetRef", mapper.valueToTree(targetRefDTO));
        ItemDeltaDTO itemDeltaDTO = new ItemDeltaDTO(ModificationType.ADD, "assignment", targetRef);
        ObjectNode itemDeltaNode = mapper.valueToTree(itemDeltaDTO);
        ObjectNode objectModificationNode = mapper.createObjectNode();
        objectModificationNode.set("itemDelta", itemDeltaNode);
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("objectModification", objectModificationNode);
        return mapper.writeValueAsString(rootNode);
    }

    public int assignRole(String roleName) throws Exception {

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
