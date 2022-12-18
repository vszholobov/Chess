package com.testirovanie.lab4;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import com.testirovanie.lab4.move.Move;
import com.testirovanie.lab4.move.MoveFactory;
import com.testirovanie.lab4.move.validation.MoveValidatorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveFigureService {
    private final MoveFactory moveFactory;
    private final MoveValidatorStrategy moveValidatorStrategy;

    public Move move(Field departure, Field destination) {
        Move move = moveFactory.createMove(departure, destination);
        moveValidatorStrategy.validate(move);
        moveFigure(departure, destination);
        return move;
    }

    private void moveFigure(Field departure, Field destination) {
        Figure figure = departure.getFigure();
        destination.setFigure(figure);
        departure.clear();
    }
}
