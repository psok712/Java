package domain.handlerFile;

import domain.entity.*;

import java.io.*;

public class CsvFileReadHandlerImpl implements CsvFileReadHandler {

    private String mPathToFile;

    public CsvFileReadHandlerImpl() {
    }

    public CsvFileReadHandlerImpl(String path) {
        mPathToFile = path;
    }

    public String getPathToFile() {
        return mPathToFile;
    }

    public void setPathToFile(String path) {
        mPathToFile = path;
    }

    @Override
    public StorageSessions readFile() throws IOException {
        fileExist(getPathToFile());

        BufferedReader csvReader = new BufferedReader(new FileReader(getPathToFile()));
        String line = csvReader.readLine();
        StorageSessions resultReadFile = new StorageSessions();

        while ((line = csvReader.readLine()) != null) {
            String[] dataFile = line.split(";");
            DataOnFilm data = new DataOnFilm();

            Date date = new Date(dataFile[1]);
            Time time = new Time(dataFile[2]);
            SeatsOnFilm seats = new SeatsOnFilm(dataFile[3]);

            data.setData(new SessionOnFilm(date, time, seats));

            resultReadFile.add(new NameFilm(dataFile[0]), data);
        }

        csvReader.close();

        return resultReadFile;
    }

    @Override
    public void fileExist(String pathToFile) throws IOException {
        File csvFile = new File(pathToFile);
        if (!csvFile.isFile()) {
            throw new IOException("File not exist!");
        }
    }
}
