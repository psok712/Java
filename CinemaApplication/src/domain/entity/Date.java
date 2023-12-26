package domain.entity;

public class Date {
    private String mDate;

    public Date() {
        mDate = "1 January 1970";
    }

    public Date(String date) {
        mDate = date;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    @Override
    public String toString() {
        return mDate;
    }
}
