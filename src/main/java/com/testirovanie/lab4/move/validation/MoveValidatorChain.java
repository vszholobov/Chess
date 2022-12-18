package com.testirovanie.lab4.move.validation;

import com.testirovanie.lab4.move.Move;
import com.testirovanie.lab4.figure.FigureType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Combines move validators.
 * Each figure has its own set of validators
 */
@RequiredArgsConstructor
public class MoveValidatorChain {
    private final List<MoveValidator> validators;
    @Getter
    private final FigureType figureType;

    public void validate(Move move) {
        validators.forEach(v -> v.validate(move));
    }
}
