package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;

public record DefaultMove(Field departure, Field destination, Figure figure) implements Move {
}
