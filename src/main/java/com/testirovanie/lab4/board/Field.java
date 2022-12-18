package com.testirovanie.lab4.board;

import com.testirovanie.lab4.figure.Figure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Field {
    private int row;
    private int col;
    @Setter
    private Figure figure;

    public boolean isBusy() {
        return figure != null;
    }

    public void clear() {
        this.figure = null;
    }
}
