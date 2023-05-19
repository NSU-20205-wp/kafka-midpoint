package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

import java.util.List;

public class ObjectDeltaDTO {

    private String objectType;
    private String changeType;
    private String oid;
    private List<ItemDeltaDTO> itemDelta;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public List<ItemDeltaDTO> getItemDelta() {
        return itemDelta;
    }

    public void setItemDelta(List<ItemDeltaDTO> itemDelta) {
        this.itemDelta = itemDelta;
    }
}
