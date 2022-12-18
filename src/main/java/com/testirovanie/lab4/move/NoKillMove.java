package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.ColumnLetter;
import com.testirovanie.lab4.figure.Figure;

public record NoKillMove(Field departure, Field destination, Figure figure) implements Move {
    @Override
    public String stringRepresentation() {
        return figure.type().getChessName() + " " +
                ColumnLetter.fromCol(departure.getCol()) + departure.getRow() +
                " - " +
                ColumnLetter.fromCol(destination.getCol()) + destination.getRow();
    }
}
