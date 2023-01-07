package com.vszholobov.chess.model.move;

import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;

public record KillMove(Field departure, Field destination, Figure figure, Figure killedFigure) implements Move {
}
