package com.testirovanie.lab4.board.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.ColEnum;
import com.testirovanie.lab4.figure.FigureProperties;

public record NoKillMove(Field toField, FigureProperties figureProperties) implements Move {
    @Override
    public String stringRepresentation() {
        return figureProperties.type().getChessName() + " " +
                ColEnum.fromCol(figureProperties.field().getCol()) + figureProperties.field().getRow() +
                " - " +
                ColEnum.fromCol(toField.getCol()) + toField.getRow();
    }
}
