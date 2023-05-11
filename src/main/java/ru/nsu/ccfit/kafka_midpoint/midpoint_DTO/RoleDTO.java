package ru.nsu.ccfit.kafka_midpoint.midpoint_DTO;

import java.util.List;

public class RoleDTO {
    private String name;
    private String displayName;
    private String description;
    private List<AssignmentDTO> assignments;
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public RoleDTO() {
    }

    @Override
    public String toString() {
        return "RoleWithAssignmentsDTO{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", assignments=" + assignments +
                '}';
    }

    public RoleDTO(String name, String displayName, String description, List<AssignmentDTO> assignments) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.assignments = assignments;
    }

    // геттеры и сеттеры для всех полей

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}
