package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.ColumnLetter;
import com.testirovanie.lab4.figure.Figure;

public record KillMove(Field departure, Field destination, Figure figure, Figure killedFigure)
        implements Move {
    // TODO: extract service
    @Override
    public String stringRepresentation() {
        return figure.type().getChessName() + " " +
                ColumnLetter.fromCol(departure.getCol()) + departure.getRow() +
                " x " +
                ColumnLetter.fromCol(destination.getCol()) + destination.getRow();
    }
}
