package domain;

import data.CinemaAccount;
import presentation.model.ConsoleModel;

public interface UserInputHandler {
    public int readUserInt(int left, int right);
    int[] selectRowAndColumn(Object[] films, CinemaAccount account,
                             UserInputHandlerImpl input, ConsoleModel output);
    int[] inputFilmAndSessionSeat(Object[] films, CinemaAccount account,
                                UserInputHandlerImpl input, ConsoleModel output);

    void changeData(Object[] films, CinemaAccount account,
                          UserInputHandlerImpl input, ConsoleModel output);
}
