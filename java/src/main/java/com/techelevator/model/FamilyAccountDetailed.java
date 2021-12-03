package com.techelevator.model;

public class FamilyAccountDetailed {

    private String username;
    private Long familyId;
    private Long userId;

    public FamilyAccountDetailed() {
    }

    public FamilyAccountDetailed(String username, Long familyId, Long userId) {
        this.username = username;
        this.familyId = familyId;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FamilyAccountDetailed{" +
                "username='" + username + '\'' +
                ", familyId=" + familyId +
                ", userId=" + userId +
                '}';
    }
}
