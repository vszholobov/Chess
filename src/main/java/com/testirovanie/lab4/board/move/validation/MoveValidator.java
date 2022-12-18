package com.testirovanie.lab4.board.move.validation;

import com.testirovanie.lab4.board.move.Move;

@FunctionalInterface
public interface MoveValidator {
    void validate(Move move);
}
