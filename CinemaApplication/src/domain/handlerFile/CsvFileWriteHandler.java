package domain.handlerFile;

import data.RuntimeCinemaAccount;

import java.io.IOException;

public interface CsvFileWriteHandler {
    void recordToFile(RuntimeCinemaAccount data) throws IOException;
}
