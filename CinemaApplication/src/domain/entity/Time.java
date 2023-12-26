package domain.entity;

import java.util.ArrayList;

public class Time {
    private String mTime;

    public Time() {
        mTime = "0:00";
    }

    public Time(String time) {
        mTime = time;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public boolean comparisonTime(String timeNow) {
        String[] hourAndMin = mTime.split(":");
        String[] hourAndMinNow = timeNow.split(":");

        return Integer.parseInt(hourAndMin[0]) < Integer.parseInt(hourAndMinNow[0]) ||
                (Integer.parseInt(hourAndMin[0]) == Integer.parseInt(hourAndMinNow[0]) &&
                Integer.parseInt(hourAndMin[1]) < Integer.parseInt(hourAndMinNow[1]));
    }

    @Override
    public String toString() {
        return mTime;
    }
}
