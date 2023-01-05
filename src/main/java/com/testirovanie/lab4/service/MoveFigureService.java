package com.testirovanie.lab4.service;

import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.figure.Figure;
import com.testirovanie.lab4.model.move.Move;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveFigureService {
    private final MoveFactory moveFactory;
    private final MoveValidationService moveValidationService;

    public Move move(Field departure, Field destination) {
        Move move = moveFactory.createMove(departure, destination);
        moveValidationService.validate(move);
        moveFigure(departure, destination);
        return move;
    }

    private void moveFigure(Field departure, Field destination) {
        Figure figure = departure.getFigure();
        destination.setFigure(figure);
        departure.clear();
    }
}
