package com.testirovanie.lab4.move.validation;

import com.testirovanie.lab4.move.Move;

@FunctionalInterface
public interface MoveValidator {
    void validate(Move move);
}
