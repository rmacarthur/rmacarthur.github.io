package com.techelevator.dao;

import com.techelevator.model.Reward;

import java.util.List;

public interface RewardDao {

    List<Reward> getAllRewards(Long familyId);

    void addRewardUser(Reward rewardToAdd);

    void addRewardFamily(Reward rewardToAdd);

    void removeReward(Long rewardId);

    void updateReward(Reward reward);

    Reward getRewardById(Long rewardId);
}
