package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

public class TargetRefDTO {
    private String oid;
    private String type;

    public TargetRefDTO() {
    }

    public TargetRefDTO(String oid, String type) {
        this.oid = oid;
        this.type = type;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
