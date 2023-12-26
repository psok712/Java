package domain.entity;

import java.util.ArrayList;

public class DataOnFilm {
    private final SessionOnFilm mData;

    public DataOnFilm() {
        mData = new SessionOnFilm();
    }

    public DataOnFilm(DataOnFilm data) {
        var inf = data.getData();
        mData = new SessionOnFilm(inf.getDate(), inf.getTime(), inf.getSeat());
    }

    public SessionOnFilm getData() {
        return mData;
    }

    public void setData(SessionOnFilm sesFilm) {
        mData.setDate(sesFilm.getDate());
        mData.setTime(sesFilm.getTime());
        mData.setSeat(sesFilm.getSeat());
    }

    @Override
    public String toString() {
        return mData.toString();
    }
}
