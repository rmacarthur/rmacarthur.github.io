package com.techelevator.model;

import java.sql.Date;

public class ReadingActivity {

    private long activityId;
    private long bookId;
    private long readerId;
    private Date dateEntered;
    private int timeInMinutes;
    private String readingType;
    private boolean isCompleted;
    private String notes;

    public ReadingActivity() {

    }

    public ReadingActivity(long activityId, long bookId, long readerId, Date dateEntered,
                           int timeInMinutes, String readingType, boolean isCompleted, String notes){

        this.activityId = activityId;
        this.bookId = bookId;
        this.readerId = readerId;
        this.dateEntered = dateEntered;
        this.timeInMinutes = timeInMinutes;
        this.readingType = readingType;
        this.isCompleted = isCompleted;
        this.notes = notes;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public String getReadingType() {
        return readingType;
    }

    public void setReadingType(String readingType) {
        this.readingType = readingType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "ReadingActivity{" +
                "activityId=" + activityId +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", dateEntered=" + dateEntered +
                ", timeInMinutes=" + timeInMinutes +
                ", readingType='" + readingType + '\'' +
                ", isCompleted=" + isCompleted +
                ", notes='" + notes + '\'' +
                '}';
    }
}
