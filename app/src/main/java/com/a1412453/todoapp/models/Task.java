package com.a1412453.todoapp.models;

/**
 * Created by Pham Minh Sang on 09/11/2016.
 */
public class Task {

    private String mName;
    private String mDate;
    private String mNotes;
    private Integer mPriorityLevel;
    private Integer mStatus;

    public Task(String mName, String mDate, String mNotes, Integer mPriorityLevel, Integer mStatus) {
        this.mName = mName;
        this.mDate = mDate;
        this.mNotes = mNotes;
        this.mPriorityLevel = mPriorityLevel;
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

    public Integer getPriorityLevel() {
        return mPriorityLevel;
    }

    public void setPriorityLevel(Integer mPriorityLevel) {
        this.mPriorityLevel = mPriorityLevel;
    }

    public Integer getStatus() {
        return mStatus;
    }

    public void setStatus(Integer mStatus) {
        this.mStatus = mStatus;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
