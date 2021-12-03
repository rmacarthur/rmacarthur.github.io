package com.techelevator.model;

import java.util.Date;

public class Reward {

    private Long rewardId;
    private Long familyId;
    private Long minutesRequired;
    private Date startDate;
    private Date endDate;
    private Long maxRecipients;
    private String description;

    public Reward() {
    }

    public Reward(Long rewardId, Long familyId, Long minutesRequired, Date startDate,
                  Date endDate, Long maxRecipients, String description){
        this.rewardId = rewardId;
        this.familyId = familyId;
        this.minutesRequired = minutesRequired;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRecipients = maxRecipients;
        this.description = description;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }


    public Long getMinutesRequired() {
        return minutesRequired;
    }

    public void setMinutesRequired(Long minutesRequired) {
        this.minutesRequired = minutesRequired;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getMaxRecipients() {
        return maxRecipients;
    }

    public void setMaxRecipients(Long maxRecipients) {
        this.maxRecipients = maxRecipients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "Reward{" +
                "reward id= " + rewardId + '\'' +
                ", family id= " + familyId + '\'' +
                ", minutes required= " + minutesRequired + '\'' +
                ", start date= " + startDate + '\'' +
                ", end date= " + endDate + '\'' +
                ", max recipients= " + maxRecipients + '\'' +
                ", description= " + description +
                        '}';
    }
}
