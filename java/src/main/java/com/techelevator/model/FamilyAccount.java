package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FamilyAccount {

    private String familyName;
    private Long familyId;

    public FamilyAccount() {
    }

    public FamilyAccount(String familyName, Long familyId) {
        this.familyName = familyName;
        this.familyId = familyId;
    }


    public Long getFamilyId() {
        return familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "FamilyAccount{" +
                "familyName='" + familyName + '\'' +
                '}';
    }
}