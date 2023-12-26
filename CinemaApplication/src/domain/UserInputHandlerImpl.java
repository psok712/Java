package domain;

import data.CinemaAccount;
import domain.entity.*;
import exception.FilmNotFoundException;
import presentation.model.ConsoleModel;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInputHandlerImpl implements UserInputHandler {

    @Override
    public int readUserInt(int left, int right) {
        ConsoleModel output = new ConsoleModel();

        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                int choiceUs = in.nextInt() - 1;
                CinemaAccountValidatorImpl validator = new CinemaAccountValidatorImpl();
                validator.validAnswerNumber(choiceUs, left, right);
                return choiceUs;
            } catch (FilmNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                output.message = "You entered an incorrect value!";
                System.out.println(output.message);
            }
            output.message = "Try entering again:";
            System.out.print(output.message);
        }
    }

    @Override
    public int[] selectRowAndColumn(Object[] films, CinemaAccount account,
                                    UserInputHandlerImpl input, ConsoleModel output) {
        int[] userResponse;
        userResponse = inputFilmAndSessionSeat(films, account, input, output);
        var arr = inputRowAndPlace(account, input, output);

        return new int[]{userResponse[0], userResponse[1], arr[0], arr[1]};
    }

    public int[] selectRowAndColumnMark(Object[] films, CinemaAccount account,
                                        UserInputHandlerImpl input, ConsoleModel output) {
        int userResponse = inputFilmAndSession(films, account, input, output);

        var arr = inputRowAndPlace(account, input, output);

        return new int[]{userResponse, arr[0], arr[1]};
    }

    private int[] inputRowAndPlace(CinemaAccount account, UserInputHandlerImpl input, ConsoleModel output) {
        int row, column;

        output.message = "Select a row:";
        System.out.print(output.message);
        row = input.readUserInt(0, SeatsOnFilm.COUNT_ROW);
        output.message = "Choose a place:";
        System.out.print(output.message);
        column = input.readUserInt(0, SeatsOnFilm.COUNT_SEATS);

        return new int[]{row, column};
    }
    @Override
    public int[] inputFilmAndSessionSeat(Object[] films, CinemaAccount account,
                                       UserInputHandlerImpl input, ConsoleModel output) {
        ArrayList<DataOnFilm> sessionOnFilm;
        int userResponse, userFilm;

        System.out.println(output.message);
        printFilm(films);

        output.message = "Enter movie number:";
        System.out.print(output.message);
        userFilm = input.readUserInt(0, films.length);
        sessionOnFilm = account.getFilmSessions().getSessionsForFilm((NameFilm) films[userFilm]);
        printSessions(sessionOnFilm);

        output.message = "Enter sessions number:";
        System.out.print(output.message);
        userResponse = input.readUserInt(0, sessionOnFilm.size());

        output.message = "Seats for this session:\n";
        System.out.print(output.message);
        output.message = sessionOnFilm.get(userResponse).getData().getSeat().toString();
        System.out.print(output.message);

        return new int[]{userFilm, userResponse};
    }

    public int inputFilmAndSession(Object[] films, CinemaAccount account,
                                       UserInputHandlerImpl input, ConsoleModel output) {
        ArrayList<DataOnFilm> sessionOnFilm;
        int userResponse;

        System.out.println(output.message);
        printFilm(films);

        output.message = "Enter movie number:";
        System.out.print(output.message);
        userResponse = input.readUserInt(0, films.length);
        sessionOnFilm = account.getFilmSessions().getSessionsForFilm((NameFilm) films[userResponse]);
        printSessions(sessionOnFilm);

        output.message = "Enter sessions number:";
        System.out.print(output.message);
        userResponse = input.readUserInt(0, sessionOnFilm.size());

        return userResponse;
    }

    @Override
    public void changeData(Object[] films, CinemaAccount account,
                                 UserInputHandlerImpl input, ConsoleModel output) {
        ArrayList<DataOnFilm> sessionOnFilm;
        int[] arr = inputChange(films, account, output, input);
        int userResponse = arr[0];
        int userRefactor = arr[1];
        sessionOnFilm = account.getFilmSessions().getSessionsForFilm((NameFilm) films[userResponse]);

        switch (userRefactor) {
            case 1: {
                changeName(account, sessionOnFilm, output, userResponse);
            } break;
            case 2: {
                changeDateAndTime(sessionOnFilm, output, userResponse);
            } break;
            default: break;
        }
    }

    private void changeDateAndTime(ArrayList<DataOnFilm> sessionOnFilm,
                                   ConsoleModel output, int userResponse) {
        Scanner in = new Scanner(System.in);
        String date, time;
        SeatsOnFilm seats = sessionOnFilm.get(userResponse).getData().getSeat();

        output.message = "Enter a new session date (in the format January 1, 1970):";
        System.out.print(output.message);
        date = in.nextLine();
        output.message = "Enter the new session time (in 0:00 format):";
        System.out.print(output.message);
        time = in.nextLine();

        sessionOnFilm.get(userResponse).setData(new SessionOnFilm(new Date(date), new Time(time), seats));

        output.message = "Everything has been successfully changed!";
        System.out.print(output.message);
    }

    private void changeName(CinemaAccount account, ArrayList<DataOnFilm> sessionOnFilm,
                            ConsoleModel output, int userResponse) {
        output.message = "Enter a new session name:";
        var data = sessionOnFilm.get(userResponse);
        Scanner in = new Scanner(System.in);

        System.out.print(output.message);
        sessionOnFilm.remove(userResponse);
        String newName = in.nextLine();
        account.getFilmSessions().add(new NameFilm(newName), data);

        output.message = "Everything has been successfully changed!";
        System.out.print(output.message);
    }

    private int[] inputChange(Object[] films, CinemaAccount account, ConsoleModel output, UserInputHandler input) {
        int userResponse;
        ArrayList<DataOnFilm> sessionOnFilm;

        System.out.println(output.message);
        printFilm(films);

        output.message = "Enter movie number:";
        System.out.print(output.message);
        userResponse = input.readUserInt(0, films.length);
        sessionOnFilm = account.getFilmSessions().getSessionsForFilm((NameFilm) films[userResponse]);
        printSessions(sessionOnFilm);

        output.message = "Enter sessions number:";
        System.out.print(output.message);
        userResponse = input.readUserInt(0, sessionOnFilm.size());

        output.message = "What data do you want to change for the session?";
        System.out.println(output.message);
        output.message = "1. Movie name\n2. Date and time.\n0. Exit";
        System.out.println(output.message);
        output.message = "Choose one from the list:";
        System.out.println(output.message);
        return new int[]{userResponse, readUserInt(-1, 2) + 1};
    }


    private void printFilm(Object[] films) {
        for (int i = 0; i < films.length; ++i) {
            System.out.printf("%d.%s\n", i + 1, films[i].toString());
        }
    }

    private void printSessions(ArrayList<DataOnFilm> sessionOnFilm) {
        ConsoleModel output = new ConsoleModel();
        for (int i = 0; i < sessionOnFilm.size(); ++i) {
            output.message = (i + 1) + ". " + sessionOnFilm.get(i).getData().getDate().toString()
                    + " at " + sessionOnFilm.get(i).getData().getTime();
            System.out.println(output.message);
        }
    }

}
