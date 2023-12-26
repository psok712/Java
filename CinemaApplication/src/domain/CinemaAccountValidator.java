package domain;

import domain.entity.SeatsOnFilm;
import exception.FilmNotFoundException;

public interface CinemaAccountValidator {
    void validAnswerNumber(int number, int left, int right) throws FilmNotFoundException;
    void validTakePlace(SeatsOnFilm seatsOnFilm, int row, int col);
    void validReturnPlace(SeatsOnFilm seatsOnFilm, int row, int col);
    void validMarkPlace(SeatsOnFilm seatsOnFilm, int row, int col);
}
