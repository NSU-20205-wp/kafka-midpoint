package ru.nsu.ccfit.kafka_midpoint.midpoint.fields;

public enum UserFields {

    NAME("name"),
    DESCRIPTION("description"),
    FULL_NAME("fullName"),
    GIVEN_NAME("givenName"),
    FAMILY_NAME("familyName"),
    ADDITIONAL_NAME("additionalName"),
    NICK_NAME("nickName"),
    HONORIFIC_PREFIX("honorificPrefix"),
    HONORIFIC_SUFFIX("honorificSuffix"),
    TITLE("title"),
    PREFERRED_LANGUAGE("preferredLanguage"),
    LOCALE("locale"),
    TIMEZONE("timezone"),
    EMAIL_ADDRESS("emailAddress"),
    TELEPHONE_NUMBER("telephoneNumber"),
    EMPLOYEE_NUMBER("employeeNumber"),
    EMPLOYEE_TYPE("employeeType"),
    COST_CENTER("costCenter"),
    ORGANIZATION("organization"),
    ORGANIZATIONAL_UNIT("organizationalUnit"),
    LOCALITY("locality"),
    CREDENTIALS("credentials"),
    ACTIVATION("activation"),
    ASSIGNMENT("assignment"),
    LINK_REF("linkRef");

    private final String value;

    UserFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
