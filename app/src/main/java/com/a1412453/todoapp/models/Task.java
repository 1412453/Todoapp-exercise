package com.a1412453.todoapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pham Minh Sang on 09/11/2016.
 */
public class Task implements Serializable {

    @SerializedName("id") private int mId;
    @SerializedName("name") private String mName;
    @SerializedName("date") private String mDate;
    @SerializedName("note") private String mNotes;
    @SerializedName("prior_level") private String mPriorityLevel;
    @SerializedName("status") private String mStatus;

    public Task(){
    }

    public Task(String mName, String mDate, String mNotes, String mPriorityLevel, String mStatus) {
        this.mName = mName;
        this.mDate = mDate;
        this.mNotes = mNotes;
        this.mPriorityLevel = mPriorityLevel;
        this.mStatus = mStatus;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
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
