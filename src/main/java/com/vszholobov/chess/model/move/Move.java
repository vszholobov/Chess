package com.vszholobov.chess.model.move;

import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;

public interface Move {
    Field destination();

    Field departure();

    Figure figure();
}
