package ru.nsu.ccfit.kafka_midpoint.midpoint.dtos;

public class ConstructionDTO {
    private String kind;
    private String intent;
    private String script;

    public ConstructionDTO() {}

    public ConstructionDTO(String kind, String intent, String script) {
        this.kind = kind;
        this.intent = intent;
        this.script = script;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
