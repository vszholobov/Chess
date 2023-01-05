package com.testirovanie.lab4.model.move;

import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.figure.Figure;

public record KillMove(Field departure, Field destination, Figure figure, Figure killedFigure) implements Move {
}
