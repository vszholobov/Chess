package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;

public record KillMove(Field departure, Field destination, Figure figure, Figure killedFigure) implements Move {
}
