package domain;

import domain.entity.SeatsOnFilm;
import exception.FilmNotFoundException;
import exception.SeatsNotFoundException;
import presentation.model.ConsoleModel;

public class CinemaAccountValidatorImpl implements CinemaAccountValidator {
    @Override
    public void validAnswerNumber(int number, int left, int right) throws FilmNotFoundException {
        ConsoleModel output = new ConsoleModel();
        if (number < left || number >= right) {
            output.message = "Unfortunately, such a number does not exist in the list.";
            throw new FilmNotFoundException(output.message);
        }
    }

    @Override
    public void validTakePlace(SeatsOnFilm seatsOnFilm, int row, int col) {
        try {
            ConsoleModel output = new ConsoleModel();
            seatsOnFilm.takeSeat(row, col);
            output.message = "The purchase was successful!\n";
            System.out.print(output.message);
        } catch (SeatsNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void validReturnPlace(SeatsOnFilm seatsOnFilm, int row, int col) {
        try {
            ConsoleModel output = new ConsoleModel();
            seatsOnFilm.returnSeat(row, col);
            output.message = "The return was successful!!\n";
            System.out.print(output.message);
        } catch (SeatsNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void validMarkPlace(SeatsOnFilm seatsOnFilm, int row, int col) {
        try {
            ConsoleModel output = new ConsoleModel();
            seatsOnFilm.markSeat(row, col);
            output.message = "It is noted that the visitor has arrived!\n";
            System.out.print(output.message);
        } catch (SeatsNotFoundException ex) {
            System.out.println("Unfortunately, such a place has not been purchased.");
        }
    }
}
