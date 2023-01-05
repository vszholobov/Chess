package com.testirovanie.lab4.model.move;

import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.figure.Figure;

public interface Move {
    Field destination();

    Field departure();

    Figure figure();
}
