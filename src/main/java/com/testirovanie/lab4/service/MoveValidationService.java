package com.testirovanie.lab4.service;

import com.testirovanie.lab4.model.figure.FigureType;
import com.testirovanie.lab4.model.move.Move;
import com.testirovanie.lab4.validation.MoveValidatorChain;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MoveValidationService {
    private final Map<FigureType, MoveValidatorChain> moveValidatorChains;

    public void validate(Move move) {
        moveValidatorChains
                .get(move.figure().type())
                .validate(move);
    }
}
