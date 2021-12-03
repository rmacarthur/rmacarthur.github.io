package com.techelevator.controller;

import com.techelevator.dao.ReadingActivityDao;
import com.techelevator.model.ReadingActivity;
import com.techelevator.model.ReadingActivityAdd;
import com.techelevator.model.ReadingActivityDetailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")


public class ReadingActivityController {

    @Autowired
    ReadingActivityDao readingActivities;

    @GetMapping("/activities")
    public List<ReadingActivity> returnActivityData() {
        logTimestamp("Getting ALL reading activities");
        List<ReadingActivity> allActivities = readingActivities.getAllReadingActivities();

        return allActivities;
    }

    @GetMapping("/activities/{activityId}")
    public ReadingActivityDetailed returnActivityByActivityId(@PathVariable long activityId) {
        logTimestamp("Getting reading activity " + activityId);
        ReadingActivityDetailed activity = readingActivities.getActivityByActivityId(activityId);

        return activity;
    }

    @GetMapping("/activities/forReader/{readerId}")
    public List<ReadingActivityDetailed> returnActivitiesByReaderId(@PathVariable long readerId) {
        logTimestamp("Getting ALL reading activities for reader id " + readerId);
        List<ReadingActivityDetailed> allActivitiesForReader = readingActivities.getActivitiesByReaderId(readerId);

        return allActivitiesForReader;
    }

    @GetMapping("/activities/forFamily/{familyId}")
    public List<ReadingActivityDetailed> returnActivitiesByFamilyId(@PathVariable long familyId,
                                                                    Principal principal) {
        logTimestamp("Getting ALL reading activities for family id " + familyId);
        List<ReadingActivityDetailed> allActivitiesForFamily = readingActivities.getActivitiesByFamilyId(familyId);

        return allActivitiesForFamily;
    }

    @GetMapping("/activities/totalMinutes/{readerId}")
    public int returnTotalMinsByReaderId(@PathVariable long readerId) {
        logTimestamp("Getting total reading activity minutes for reader id " + readerId);
        int totalMins = readingActivities.getTotalReadingMinsByReaderId(readerId);

        return totalMins;
    }

    @PostMapping("/addActivity/")
    public void createActivity(@RequestBody ReadingActivityAdd newActivity){
        logTimestamp("Adding new reading activity.");
        readingActivities.addActivity(newActivity);
    }

    @PutMapping("/editActivity/{activityId}")
    public ReadingActivityDetailed updateActivityById(@PathVariable long activityId,
                                              @RequestBody ReadingActivity activityToUpdate){
        logTimestamp("Updating reading activity " + activityId);
        readingActivities.updateActivity(activityToUpdate, activityId);

        return returnActivityByActivityId(activityId);
    }

    @DeleteMapping("/removeActivity/{activityId}")
    public void deleteActivity(@PathVariable long activityId,
                               Principal principal){
        logTimestamp("Activity " + activityId + " removed by user: " + principal.getName());
    }


    static void logTimestamp(String msg) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(msg + "at" + timestamp);
    }
}
