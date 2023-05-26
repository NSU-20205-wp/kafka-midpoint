package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

import ru.nsu.ccfit.kafka_midpoint.midpoint.ModificationType;

public class ItemDeltaDTO {

    private ModificationType modificationType;
    private String path;
    private Object value;

    public ItemDeltaDTO(ModificationType modificationType, String path, Object value) {
        this.modificationType = modificationType;
        this.path = path;
        this.value = value;
    }

    public ModificationType getModificationType() {
        return modificationType;
    }

    public void setModificationType(ModificationType modificationType) {
        this.modificationType = modificationType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
