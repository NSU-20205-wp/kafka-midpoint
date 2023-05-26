package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

public class AssignmentDTO {
    private String targetType;
    private String targetOid;
    private String relation;
    private ConstructionDTO construction;

    /*
    targetType - тип целевого объекта (например, "Role" или "User").
    targetOid - идентификатор целевого объекта в MidPoint.
    relation - тип отношения между ролью и целевым объектом (например, "org:approver" или "org:manager").
    construction - объект, который содержит информацию о способе вычисления права доступа к ресурсу.
    construction может быть пустым, если право доступа к ресурсу не вычисляется динамически.
 */

    @Override
    public String toString() {
        return "AssignmentDTO{" +
                "targetType='" + targetType + '\'' +
                ", targetOid='" + targetOid + '\'' +
                ", relation='" + relation + '\'' +
                ", construction=" + construction +
                '}';
    }

    public AssignmentDTO() {
    }

    public AssignmentDTO(String targetType, String targetOid, String relation, ConstructionDTO construction) {
        this.targetType = targetType;
        this.targetOid = targetOid;
        this.relation = relation;
        this.construction = construction;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetOid() {
        return targetOid;
    }

    public void setTargetOid(String targetOid) {
        this.targetOid = targetOid;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public ConstructionDTO getConstruction() {
        return construction;
    }

    public void setConstruction(ConstructionDTO construction) {
        this.construction = construction;
    }
}
