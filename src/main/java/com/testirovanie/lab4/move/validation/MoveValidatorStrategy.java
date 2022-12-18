package com.testirovanie.lab4.move.validation;

import com.testirovanie.lab4.move.Move;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoveValidatorStrategy {
    // TODO: map
    private final List<MoveValidatorChain> moveValidatorChains;

    public void validate(Move move) {
        moveValidatorChains
                .stream()
                .filter(c -> c.getFigureType().equals(move.figure().type()))
                .findFirst()
                .ifPresent(c -> c.validate(move));
    }
}
