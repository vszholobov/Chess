package com.testirovanie.lab4.validation;

import com.testirovanie.lab4.model.figure.FigureType;
import com.testirovanie.lab4.model.move.Move;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Combines move validators.
 * Each figure type has its own chain of validators
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
