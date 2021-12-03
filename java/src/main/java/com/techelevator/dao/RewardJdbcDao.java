package com.techelevator.dao;

import com.techelevator.model.Reward;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RewardJdbcDao implements RewardDao{

    private JdbcTemplate template;

    public RewardJdbcDao(DataSource dataSource){template = new JdbcTemplate(dataSource);}


    @Override
    public List<Reward> getAllRewards(Long familyId) {
            List<Reward> rewardList = new ArrayList<>();
            String sql = "SELECT * FROM rewards where family_id=?";
            SqlRowSet result = template.queryForRowSet(sql, familyId);
            while (result.next()){
                rewardList.add(mapResultToReward(result));
            }
            return rewardList;
        }

    @Override
    public void addRewardUser(Reward rewardToAdd) {
        String sql = "INSERT INTO rewards (minutes_required, " +
                "end_date, max_recipients, description)" + "values(?, ?, ?, ?)";
        template.update(sql, rewardToAdd.getMinutesRequired(),  rewardToAdd.getEndDate(),
                rewardToAdd.getMaxRecipients(), rewardToAdd.getDescription());
    }

    public void addRewardFamily(Reward rewardToAdd) {
        String sql = "INSERT INTO rewards (family_id, minutes_required, " +
                "end_date, max_recipients, description)" + "values(?, ?, ?, ?, ?)";
        template.update(sql, rewardToAdd.getFamilyId(),
                rewardToAdd.getMinutesRequired(), rewardToAdd.getEndDate(),
                rewardToAdd.getMaxRecipients(), rewardToAdd.getDescription());
    }
    @Override
    public void removeReward(Long rewardId) {
        String sql = "DELETE FROM rewards WHERE reward_id = ?";
        template.update(sql, rewardId);
    }

    @Override
    public void updateReward(Reward reward) {
        String sql = "UPDATE reward" +
                "SET reward_id = ?, family_id = ?, minutes_required = ?, start_date = ?," +
                "end_date = ?, max_recipients = ?, description = ?" + "WHERE reward_id = ?";
        template.update(sql, reward.getRewardId(), reward.getFamilyId(), reward.getMinutesRequired(),
                reward.getStartDate(), reward.getEndDate(), reward.getMaxRecipients(), reward.getDescription());
    }

    @Override
    public Reward getRewardById(Long rewardId) {
        String sql = "Select * from rewards WHERE reward_id = ?";
        SqlRowSet result = template.queryForRowSet(sql, rewardId);
        Reward returnItem= new Reward();
        if (result.next()){
            returnItem = mapResultToReward(result);
        }
        return returnItem;
    }

    private Reward mapResultToReward(SqlRowSet result) {
        Long rewardId = result.getLong("reward_id");
        Long familyId = result.getLong("family_id");
        Long minutesRequired = result.getLong("minutes_required");
        Date startDate = result.getDate("start_date");
        Date endDate = result.getDate("end_date");
        Long maxRecipients = result.getLong("max_recipients");
        String description = result.getString("description");

        Reward reward = new Reward(rewardId, familyId, minutesRequired, startDate,
                endDate, maxRecipients, description);
        return reward;
    }
}
