package com.testirovanie.lab4.board.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.ColEnum;
import com.testirovanie.lab4.figure.FigureProperties;

public record KillMove(Field toField, FigureProperties figureProperties, FigureProperties killedFigureProperties)
        implements Move {
    @Override
    public String stringRepresentation() {
        return figureProperties.type().getChessName() + " " +
                ColEnum.fromCol(figureProperties.field().getCol()) + figureProperties.field().getRow() +
                " x " +
                ColEnum.fromCol(toField.getCol()) + toField.getRow();
    }
}
