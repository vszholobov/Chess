package com.testirovanie.lab4.model.move;

import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.figure.Figure;

public record DefaultMove(Field departure, Field destination, Figure figure) implements Move {
}
