package com.testirovanie.lab4.figure;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.board.move.Move;
import com.testirovanie.lab4.board.move.MoveFactory;
import com.testirovanie.lab4.board.move.validation.MoveValidatorChain;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.PostConstruct;
import java.util.Optional;

@AllArgsConstructor
public class Figure {
    @Getter
    private FigureProperties properties;
    private final MoveValidatorChain moveValidatorChain;
    private final MoveFactory moveFactory;

    @PostConstruct
    public void initPostConstruct() {
        properties.field().setFigure(this);
    }

    public Move goToPosition(Field destination) {
        Move move = moveFactory.createMove(destination, properties);
        moveValidatorChain.validate(move);
        Optional.ofNullable(destination.getFigure()).ifPresent(Figure::kill);
        moveToField(destination);
        return move;
    }

    private void moveToField(Field destination) {
        properties.field().clear();
        destination.setFigure(this);
        properties = properties.withField(destination);
    }

    public void kill() {
        properties = properties.withField(null);
    }
}
