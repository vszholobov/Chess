package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;

public interface Move {
    Field destination();

    Field departure();

    Figure figure();
}
