package com.testirovanie.lab4.figure;

import com.testirovanie.lab4.board.Field;
import lombok.With;

@With
public record FigureProperties(FigureType type, Field field, FigureSide side) {
    public boolean isDead() {
        return field == null;
    }
}
