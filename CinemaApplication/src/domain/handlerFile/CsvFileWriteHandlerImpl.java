package domain.handlerFile;

import data.RuntimeCinemaAccount;
import domain.entity.*;
import domain.handlerFile.CsvFileWriteHandler;

import java.io.*;

public class CsvFileWriteHandlerImpl implements CsvFileWriteHandler {


    private String mPathToFile;

    public CsvFileWriteHandlerImpl() {
    }

    public CsvFileWriteHandlerImpl(String path) {
        mPathToFile = path;
    }

    public String getPathToFile() {
        return mPathToFile;
    }

    public void setPathToFile(String path) {
        mPathToFile = path;
    }

    @Override
    public void recordToFile(RuntimeCinemaAccount data) throws IOException {
        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(getPathToFile()));
        var hashMap = data.getFilmSessions().getStorageSessions();

        csvWriter.append("Film;Date;Time;Seats\n");
        for (var film : hashMap.keySet()) {
            for (var el : hashMap.get(film)) {
                csvWriter.append(String.format("%s;%s\n", film.toString(), el.toString()));
            }
        }

        csvWriter.close();
    }
}
