package com.vszholobov.chess.model.move;

import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;

public record DefaultMove(Field departure, Field destination, Figure figure) implements Move {
}
