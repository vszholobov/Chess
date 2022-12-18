package com.testirovanie.lab4.board.move.validation;

import com.testirovanie.lab4.board.move.Move;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Combines move validators.
 * Each figure has its own set of validators
 */
@RequiredArgsConstructor
public class MoveValidatorChain {
    private final List<MoveValidator> validators;

    public void validate(Move move) {
        validators.forEach(v -> v.validate(move));
    }
}
