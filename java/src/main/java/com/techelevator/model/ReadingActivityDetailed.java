package com.techelevator.model;

import java.sql.Date;

public class ReadingActivityDetailed {

    private String readerName;
    private String bookTitle;
    private Date dateEntered;
    private int timeInMinutes;
    private String readingType;
    private boolean isCompleted;
    private String notes;

    public ReadingActivityDetailed() {

    }

    public ReadingActivityDetailed(String readerName, String bookTitle,
                                   Date dateEntered, int timeInMinutes, String readingType,
                                   boolean isCompleted, String notes){

        this.readerName = readerName;
        this.bookTitle = bookTitle;
        this.dateEntered = dateEntered;
        this.timeInMinutes = timeInMinutes;
        this.readingType = readingType;
        this.isCompleted = isCompleted;
        this.notes = notes;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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
        return "ReadingActivityDetailed{" +
                "readerName='" + readerName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", dateEntered=" + dateEntered +
                ", timeInMinutes=" + timeInMinutes +
                ", readingType='" + readingType + '\'' +
                ", isCompleted=" + isCompleted +
                ", notes='" + notes + '\'' +
                '}';
    }
}
