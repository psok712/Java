package domain;

import domain.entity.DataOnFilm;
import domain.entity.NameFilm;
import domain.entity.StorageSessions;
import presentation.model.ConsoleModel;

public interface CinemaAccountController {
    void addSessions(NameFilm nameFilm, DataOnFilm dataOnFilm);
    void returnTicket();
    void printSession();
    void changeData();
    public void markCame();
}
