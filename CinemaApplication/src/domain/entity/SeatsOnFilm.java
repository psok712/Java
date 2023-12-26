package domain.entity;

import exception.SeatsNotFoundException;

import java.util.ArrayList;

public class SeatsOnFilm {
    private Integer[][] mSeatSession;
    public static final int COUNT_ROW = 5;
    public static final int COUNT_SEATS = 5;

    public SeatsOnFilm() {
        mSeatSession = new Integer[COUNT_ROW][COUNT_SEATS];

        for (int i = 0; i < COUNT_ROW; ++i) {
            for (int j = 0; j < COUNT_SEATS; ++j) {
                mSeatSession[i][j] = 0;
            }
        }
    }

    public SeatsOnFilm(String line) {
        mSeatSession = new Integer[COUNT_ROW][COUNT_SEATS];

        for (int i = 0; i < COUNT_ROW; ++i) {
            for (int j = 0; j < COUNT_SEATS; ++j) {
                mSeatSession[i][j] = line.charAt(COUNT_ROW * i + j) - 48;
            }
        }
    }

    public String getSeatsOnFilm() {
        StringBuilder line = new StringBuilder(COUNT_SEATS * COUNT_ROW);

        for (int i = 0; i < COUNT_ROW; ++i) {
            for (int j = 0; j < COUNT_SEATS; ++j) {
                line.append(mSeatSession[i][j]);
            }
        }

        return line.toString();
    }

    public void takeSeat(Integer row, Integer seat) throws SeatsNotFoundException {
        if (row < 0 || row >= COUNT_ROW || seat < 0 || seat >= COUNT_SEATS) {
            throw new SeatsNotFoundException("Your seats was not found!");
        }

        if (mSeatSession[row][seat] == 1) {
            throw new SeatsNotFoundException("Error, the selected seat is already taken.");
        }

        mSeatSession[row][seat] = 1;
    }

    public void returnSeat(Integer row, Integer seat) throws SeatsNotFoundException {
        if (row < 0 || row >= COUNT_ROW || seat < 0 || seat >= COUNT_SEATS) {
            throw new SeatsNotFoundException("Your seats was not found!");
        }

        if (mSeatSession[row][seat] == 0) {
            throw new SeatsNotFoundException("Error, the selected seat is not occupied.");
        }

        mSeatSession[row][seat] = 0;
    }

    public void markSeat(Integer row, Integer seat) throws SeatsNotFoundException {
        if (row < 0 || row >= COUNT_ROW || seat < 0 || seat >= COUNT_SEATS) {
            throw new SeatsNotFoundException("Your seats was not found!");
        }

        if (mSeatSession[row][seat] == 0) {
            throw new SeatsNotFoundException("Error, the selected seat is not occupied.");
        }

        if (mSeatSession[row][seat] == 2) {
            throw new SeatsNotFoundException("Error, the selected seat is already taken.");
        }

        mSeatSession[row][seat] = 2;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder(COUNT_ROW * COUNT_SEATS);

        line.append("  ");
        for (int i = 1; i <= COUNT_SEATS; ++i) {
            line.append(String.format(" %d.", i));
        }
        line.append('\n');

        for (int i = 0; i < COUNT_ROW; ++i) {
            line.append(String.format("%d. ", i + 1));
            for (int j = 0; j < COUNT_SEATS; ++j) {
                line.append(mSeatSession[i][j]);
                line.append("  ");
            }
            line.append('\n');
        }

        return line.toString();
    }
}
