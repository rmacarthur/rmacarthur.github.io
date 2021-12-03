package com.techelevator.controller;

import com.techelevator.dao.FamilyAccountDao;
import com.techelevator.model.Book;
import com.techelevator.model.FamilyAccount;
import com.techelevator.model.FamilyAccountDetailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class FamilyAccountController {

    @Autowired
    FamilyAccountDao familyAccountDao;

    @GetMapping(path = {"/familyList/{familyId}"})
    public List<FamilyAccountDetailed> getFamilyList(@PathVariable Long familyId){
        logTimestamp("Getting all family members...");
        return familyAccountDao.getFamilyList(familyId);
         /*List<FamilyAccountDetailed> familyList = familyAccountDao.getFamilyList(familyId);
        System.out.println(familyList);
         return null;*/
    }

    @GetMapping(path = {"/family/{id}"})
    public Long getFamilyId(@PathVariable Long id){
        logTimestamp("Retrieving family id for current user.");

        return familyAccountDao.getFamilyId(id);
    }

    @GetMapping(path = {"/users"})
    public List<FamilyAccountDetailed> findAllUsers(){
        logTimestamp("Getting all users...");
        return familyAccountDao.findAllUsers();
    }

    @PostMapping("/addFamilyAccount")
    public void addFamilyAccount(@RequestBody FamilyAccount newFamilyAccount){
        logTimestamp("Adding new family account...");
        familyAccountDao.addFamilyAccount(newFamilyAccount);
    }

    @PutMapping("/addFamilyMember/{familyId}/{userId}")
    public void addFamilyMember(@PathVariable Long familyId, Long userId){
        logTimestamp("Adding member to family...");
        familyAccountDao.addFamilyMember(familyId, userId);
    }

    @DeleteMapping("/deleteFamilyAccount/{familyId}")
    public void deleteFamilyAccount(@PathVariable long familyId){
        familyAccountDao.removeFamilyAccount(familyId);
        logTimestamp("Removing family account " + familyId);
    }

    static void logTimestamp (String msg) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + " at " + timestamp);
    }

}
