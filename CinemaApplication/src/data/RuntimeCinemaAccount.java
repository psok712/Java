package data;

import domain.CinemaAccountControllerImpl;
import domain.entity.NameFilm;
import domain.entity.SeatsOnFilm;
import domain.entity.StorageSessions;
import domain.handlerFile.CsvFileWriteHandlerImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import static java.lang.System.exit;

public class RuntimeCinemaAccount implements CinemaAccount {
    private final StorageSessions mFilmSession;

    public RuntimeCinemaAccount() {
        mFilmSession = new StorageSessions();
    }

    public RuntimeCinemaAccount(StorageSessions storageSessions) {
        mFilmSession = storageSessions;
    }
    @Override
    public StorageSessions getFilmSessions() {
        return mFilmSession;
    }

    @Override
    public Set<NameFilm> getFilms() {
        return mFilmSession.getNameFilms();
    }

    public void executingRequest(int answerUser, CinemaAccountControllerImpl controller,
                                 CsvFileWriteHandlerImpl fileWrite) throws IOException {
        switch(answerUser) {
            case 1: {
                controller.sellTicket();
                fileWrite.recordToFile(this);
            } break;
            case 2: {
                controller.returnTicket();
                fileWrite.recordToFile(this);
            } break;
            case 3: {
                controller.printSession();
            } break;
            case 4: {
                controller.changeData();
                fileWrite.recordToFile(this);
            } break;
            case 5: {
                controller.markCame();
                fileWrite.recordToFile(this);
            }
            default: exit(0);
        }
    }
}
