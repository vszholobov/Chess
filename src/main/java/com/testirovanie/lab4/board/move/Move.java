package com.testirovanie.lab4.board.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.FigureProperties;

public interface Move {
    String stringRepresentation();

    Field toField();

    FigureProperties figureProperties();
}
