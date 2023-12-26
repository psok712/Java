package domain.handlerFile;

import domain.entity.StorageSessions;

import java.io.IOException;

public interface CsvFileReadHandler {
    StorageSessions readFile() throws IOException;
    void fileExist(String pathToFile) throws IOException;
}
