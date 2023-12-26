package data;

import domain.entity.NameFilm;
import domain.entity.SeatsOnFilm;
import domain.entity.StorageSessions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface CinemaAccount {
    StorageSessions getFilmSessions();

    Set<NameFilm> getFilms();
}
