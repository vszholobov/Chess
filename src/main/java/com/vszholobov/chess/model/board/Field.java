package com.vszholobov.chess.model.board;

import com.vszholobov.chess.model.figure.Figure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Field {
    private final int row;
    private final int col;
    @Setter
    private Figure figure;

    public boolean isBusy() {
        return figure != null;
    }

    public void clear() {
        this.figure = null;
    }
}
