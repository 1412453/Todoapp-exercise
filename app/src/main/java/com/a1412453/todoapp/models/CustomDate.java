package com.a1412453.todoapp.models;

/**
 * Created by Pham Minh Sang on 17/11/2016.
 */
public class CustomDate {

    int mDay;
    int mMonth;
    int mYear;

    public CustomDate(int mDay, int mMonth, int mYear) {
        this.mDay = mDay;
        this.mMonth = mMonth;
        this.mYear = mYear;
    }

    private String convert2Month(int mMonth){
        switch (mMonth){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "Jan";
        }
    }

    private int convert2Month(String month) {
        switch (month){
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 1;
        }
    }

    public String convert2String(){
        return convert2Month(mMonth)+ " " +Integer.toString(mDay) + " " + Integer.toString(mYear);
    }

    public CustomDate(String dateTime) {
        String[] arr = dateTime.split(" ");

        this.mDay = Integer.parseInt(arr[1]);
        this.mMonth = convert2Month(arr[0]);
        this.mYear = Integer.parseInt(arr[2]);
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int mDay) {
        this.mDay = mDay;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int mYear) {
        this.mYear = mYear;
    }

}
