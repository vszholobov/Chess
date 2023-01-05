package com.testirovanie.lab4.validation;

import com.testirovanie.lab4.model.move.Move;

@FunctionalInterface
public interface MoveValidator {
    /**
     * Validates move
     * @param move
     * @throws RuntimeException if move is wrong
     */
    void validate(Move move);
}
