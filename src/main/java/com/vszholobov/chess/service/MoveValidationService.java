package com.vszholobov.chess.service;

import com.vszholobov.chess.model.figure.FigureType;
import com.vszholobov.chess.model.move.Move;
import com.vszholobov.chess.validation.MoveValidatorChain;
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
