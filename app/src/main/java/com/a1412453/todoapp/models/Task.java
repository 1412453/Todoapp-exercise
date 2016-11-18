package com.a1412453.todoapp.models;

/**
 * Created by Pham Minh Sang on 09/11/2016.
 */
public class Task {

    private String mName;
    private String mDate;
    private String mNotes;
    private String mPriorityLevel;
    private String mStatus;

    public Task(){
    }

    public Task(String mName, String mDate, String mNotes, String mPriorityLevel, String mStatus) {
        this.mName = mName;
        this.mDate = mDate;
        this.mNotes = mNotes;
        this.mPriorityLevel = mPriorityLevel;
        this.mStatus = mStatus;
    }

    public String getPriorityLevel() {
        return mPriorityLevel;
    }

    public void setPriorityLevel(String mPriorityLevel) {
        this.mPriorityLevel = mPriorityLevel;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
