package com.vszholobov.chess.validation;

import com.vszholobov.chess.model.move.Move;

@FunctionalInterface
public interface MoveValidator {
    /**
     * Validates move
     * @param move
     * @throws RuntimeException if move is wrong
     */
    void validate(Move move);
}
