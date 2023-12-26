package domain;

import data.CinemaAccount;
import domain.entity.DataOnFilm;
import domain.entity.NameFilm;
import exception.FilmNotFoundException;
import presentation.model.ConsoleModel;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaAccountControllerImpl implements CinemaAccountController {
    private final CinemaAccount mCinemaAccount;

    public CinemaAccountControllerImpl(CinemaAccount account) {
        mCinemaAccount = account;
    }

    @Override
    public void addSessions(NameFilm nameFilm, DataOnFilm dataOnFilm) {
        mCinemaAccount.getFilmSessions().add(nameFilm, dataOnFilm);
    }

    public CinemaAccount getCinemaAccount() {
        return mCinemaAccount;
    }

    public void sellTicket() {
        ConsoleModel output = new ConsoleModel();
        output.message = "Which movie would you like to buy tickets for?";
        CinemaAccountValidatorImpl validator = new CinemaAccountValidatorImpl();
        UserInputHandlerImpl input = new UserInputHandlerImpl();
        var films = mCinemaAccount.getFilmSessions().getNameFilms().toArray();
        int[] arr = input.selectRowAndColumn(mCinemaAccount.getFilmSessions().getNameFilms().toArray(),
                mCinemaAccount, input, output);
        ArrayList<DataOnFilm> sessionOnFilm =
                mCinemaAccount.getFilmSessions().getSessionsForFilm((NameFilm) films[arr[0]]);

        validator.validTakePlace(sessionOnFilm.get(arr[1]).getData().getSeat(), arr[2], arr[3]);
    }

    @Override
    public void returnTicket() {
        ConsoleModel output = new ConsoleModel();
        output.message = "Which movie would you like to return your ticket for?";
        UserInputHandlerImpl input = new UserInputHandlerImpl();
        CinemaAccountValidatorImpl validator = new CinemaAccountValidatorImpl();
        var films = mCinemaAccount.getFilmSessions().getNameFilms().toArray();
        int[] arr = input.selectRowAndColumn(mCinemaAccount.getFilmSessions().getNameFilms().toArray(),
                mCinemaAccount, input, output);
        ArrayList<DataOnFilm> sessionOnFilm =
                mCinemaAccount.getFilmSessions().getSessionsForFilm((NameFilm) films[arr[0]]);

        validator.validReturnPlace(sessionOnFilm.get(arr[0]).getData().getSeat(), arr[1], arr[2]);
    }

    @Override
    public void printSession() {
        ConsoleModel output = new ConsoleModel();
        output.message = "What movie do you want to see?";
        UserInputHandlerImpl input = new UserInputHandlerImpl();
        input.inputFilmAndSessionSeat(mCinemaAccount.getFilmSessions().getNameFilms().toArray(),
                mCinemaAccount, new UserInputHandlerImpl(), output);
    }

    @Override
    public void changeData() {
        ConsoleModel output = new ConsoleModel();
        UserInputHandlerImpl input = new UserInputHandlerImpl();

        output.message = "Select which movie you want to change data for:";
        input.changeData(mCinemaAccount.getFilmSessions().getNameFilms().toArray(),
                mCinemaAccount, input, output);
    }

    @Override
    public void markCame() {
        ConsoleModel output = new ConsoleModel();
        output.message = "What movie do you want to mark the visitor for?";
        UserInputHandlerImpl input = new UserInputHandlerImpl();
        CinemaAccountValidatorImpl validator = new CinemaAccountValidatorImpl();
        var films = mCinemaAccount.getFilmSessions().getNameFilms().toArray();

        int[] arr = input.selectRowAndColumnMark(mCinemaAccount.getFilmSessions().getNameFilms().toArray(),
                mCinemaAccount, input, output);
        ArrayList<DataOnFilm> sessionOnFilm =
                mCinemaAccount.getFilmSessions().getSessionsForFilm((NameFilm) films[arr[0]]);


        validator.validMarkPlace(sessionOnFilm.get(arr[0]).getData().getSeat(), arr[1], arr[2]);
    }

    public int inputUserChoise(int left, int right) {
        ConsoleModel output = new ConsoleModel();

        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                int choiceUs = in.nextInt();
                CinemaAccountValidatorImpl validator = new CinemaAccountValidatorImpl();
                validator.validAnswerNumber(choiceUs, left, right);
                return choiceUs;
            } catch (Exception ex) {
                output.message = "You entered an incorrect value!";
                System.out.println(output.message);
            }
            output.message = "Try entering again:";
            System.out.print(output.message);
        }
    }
}
