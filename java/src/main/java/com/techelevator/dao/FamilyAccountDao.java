package com.techelevator.dao;

import com.techelevator.model.FamilyAccount;
import com.techelevator.model.FamilyAccountDetailed;

import java.util.HashSet;
import java.util.List;

public interface FamilyAccountDao {

   public void addFamilyAccount(FamilyAccount familyToAdd);

    public void removeFamilyAccount(Long familyId);

    public Long getFamilyId(Long userId);

   public List<FamilyAccountDetailed> getFamilyList(Long familyId);

    public List<FamilyAccountDetailed> findAllUsers();

   public void addFamilyMember(Long familyId, Long userId);


}
