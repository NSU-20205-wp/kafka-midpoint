package ru.nsu.ccfit.kafka_midpoint.midpoint.fields;

public enum RoleFields {
    NAME("name"),
    DESCRIPTION("description"),
    ROLE_TYPE("roleType"),
    DISPLAY_NAME("displayName"),
    ASSIGNMENT("assignment"),
    AUTHORIZATION("authorization"),
    RISK_LEVEL("riskLevel"),
    OWNER_REF("ownerRef"),
    APPROVER_REF("approverRef"),
    CONDITION("condition"),
    POLICY_CONSTRAINTS("policyConstraints");

    private final String value;

    RoleFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
