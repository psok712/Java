package domain.entity;

import exception.FilmNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StorageSessions {
    private final Map<NameFilm, ArrayList<DataOnFilm>> mStorageSessions;

    public StorageSessions() {
        mStorageSessions = new HashMap<>();
    }

    public StorageSessions(StorageSessions storageSessions) {
        mStorageSessions = copy(storageSessions);
    }

    public Map<NameFilm, ArrayList<DataOnFilm>> getStorageSessions() {
        return mStorageSessions;
    }

    public ArrayList<DataOnFilm> getSessionsForFilm(NameFilm film) {
        return mStorageSessions.get(film);
    }

    public int getSize() {
        return mStorageSessions.size();
    }

    public Set<NameFilm> getNameFilms() {
        return mStorageSessions.keySet();
    }

    public void add(NameFilm film, DataOnFilm info) {

        for (var movie : mStorageSessions.keySet()) {
            if (movie.equals(film)) {
                mStorageSessions.get(movie).add(info);
                return;
            }
        }

        var listInfo = new ArrayList<DataOnFilm>();
        listInfo.add(info);
        mStorageSessions.put(film, listInfo);
    }

    public ArrayList<DataOnFilm> getInformationFilm(NameFilm key) throws FilmNotFoundException {
        if (!mStorageSessions.containsKey(key)) {
            throw new FilmNotFoundException("Film don't exist in sessions!");
        }

        return mStorageSessions.get(key);
    }

    private Map<NameFilm, ArrayList<DataOnFilm>> copy(StorageSessions storageSessions) {
        var copyStorage = new HashMap<NameFilm, ArrayList<DataOnFilm>>();
        Set<NameFilm> setFilms = storageSessions.getStorageSessions().keySet();

        for (NameFilm film : setFilms) {

            copyStorage.put(film, new ArrayList<DataOnFilm>(storageSessions.getStorageSessions().get(film)));
        }

        return copyStorage;
    }
}
