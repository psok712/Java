package domain.entity;

public class NameFilm {
    private String mName;

    public NameFilm() {}

    public NameFilm(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameFilm that = (NameFilm) o;
        return mName.equals(that.getName());
    }
}
