package presentation;

import data.RuntimeCinemaAccount;
import domain.CinemaAccountControllerImpl;
import domain.handlerFile.CsvFileReadHandlerImpl;
import domain.handlerFile.CsvFileWriteHandlerImpl;
import exception.FilmNotFoundException;
import presentation.model.ConsoleModel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FilmNotFoundException {
        ConsoleModel output = new ConsoleModel();

        try {
            CsvFileReadHandlerImpl fileRead = new CsvFileReadHandlerImpl("Dataframe.csv");
            CsvFileWriteHandlerImpl fileWrite = new CsvFileWriteHandlerImpl("Dataframe.csv");
            RuntimeCinemaAccount cinemaAccount = new RuntimeCinemaAccount(fileRead.readFile());
            CinemaAccountControllerImpl controller = new CinemaAccountControllerImpl(cinemaAccount);

            while (true) {
                int answerUser;

                ConsoleOutputForUser.menu();
                output.message = "Enter the selected number:";
                System.out.print(output.message);
                answerUser = controller.inputUserChoise(0, 6);
                cinemaAccount.executingRequest(answerUser, controller, fileWrite);
            }
        } catch (IOException ex) {
            output.message = "Sorry, no such file exists.";
            System.out.println(output.message);
        } catch (Exception ex) {
            output.message = "There was an emergency, sorry :(";
            System.out.println(output.message);
        }
    }
}
