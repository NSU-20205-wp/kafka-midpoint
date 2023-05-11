package ru.nsu.ccfit.kafka_midpoint.midpoint_DTO;

public class UserDTO extends MidpointDTO{
    private String fullName;
    private String givenName;
    private String familyName;
    private String emailAddress;

    public UserDTO() {
    }

    public UserDTO(String name, String givenName, String familyName, String emailAddress) {
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
