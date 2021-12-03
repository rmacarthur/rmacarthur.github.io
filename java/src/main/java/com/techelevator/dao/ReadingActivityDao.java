package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;
import com.techelevator.model.ReadingActivityAdd;
import com.techelevator.model.ReadingActivityDetailed;

import java.util.List;

public interface ReadingActivityDao {

    List<ReadingActivity> getAllReadingActivities();
    ReadingActivityDetailed getActivityByActivityId(long activityId);
    List<ReadingActivityDetailed> getActivitiesByReaderId(long readerId);
    List<ReadingActivityDetailed> getActivitiesByFamilyId(long familyId);
    int getTotalReadingMinsByReaderId(long readerId);

    void addActivity(ReadingActivityAdd activityToAdd);
    void updateActivity(ReadingActivity activityToUpdate, long activityId);
    void deleteActivity(long activityId);

}
