package domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionOnFilm {
    private Date mDate;
    private Time mTime;
    private SeatsOnFilm mSeat;

    public SessionOnFilm() {}

    public SessionOnFilm(Date date, Time time, SeatsOnFilm seat) {
        mDate = date;
        mTime = time;
        mSeat = seat;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public SeatsOnFilm getSeat() {
        return mSeat;
    }

    public void setSeat(SeatsOnFilm seat) {
        mSeat = seat;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s", mDate.toString(), mTime.toString(), mSeat.getSeatsOnFilm());
    }
}
