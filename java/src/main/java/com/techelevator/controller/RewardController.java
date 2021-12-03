package com.techelevator.controller;

import com.techelevator.dao.RewardDao;
import com.techelevator.model.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RewardController {

    @Autowired
    RewardDao rewardDao;

    @GetMapping(path = {"/allRewards/{familyId}"})
    public List<Reward> getAllRewards(@PathVariable long familyId){
        logTimestamp("Getting all rewards");
        List<Reward> allRewards = rewardDao.getAllRewards(familyId);
        return allRewards;
    }

    @GetMapping ("/getReward/{rewardId}")
    public Reward getRewardById(@PathVariable Long rewardId){
        return rewardDao.getRewardById(rewardId);

    }

    @PostMapping("/addRewardUser")
    public void addReward(@RequestBody Reward newReward){
        logTimestamp("Adding new reward" + newReward.getRewardId());
        rewardDao.addRewardUser(newReward);
    }

    @PostMapping("/addRewardFamily")
    public void addRewardFamily(@RequestBody Reward newReward){
        logTimestamp("Adding new reward" + newReward.getRewardId());
        rewardDao.addRewardFamily(newReward);
    }


    @DeleteMapping("/removeReward/{rewardId}")
    public void deleteReward(@PathVariable Long rewardId){
        rewardDao.removeReward(rewardId);
        logTimestamp("Removing reward" + rewardId);
    }

    @PutMapping("/updateReward/{rewardId}")
    public void updateReward(@PathVariable Long rewardId){
        rewardDao.updateReward(getRewardById(rewardId));
        logTimestamp("Updating reward" + rewardId);
    }


    static void logTimestamp (String msg) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + " at " + timestamp);
    }

}
