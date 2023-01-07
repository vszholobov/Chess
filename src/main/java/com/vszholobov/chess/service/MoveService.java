package com.vszholobov.chess.service;

import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;
import com.vszholobov.chess.model.move.DefaultMove;
import com.vszholobov.chess.model.move.KillMove;
import com.vszholobov.chess.model.move.Move;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoveService {
    private final MoveValidationService moveValidationService;

    public Move move(Field departure, Field destination) {
        Move move = createMove(departure, destination);
        moveValidationService.validate(move);
        moveFigure(departure, destination);
        return move;
    }

    private void moveFigure(Field departure, Field destination) {
        Figure figure = departure.getFigure();
        destination.setFigure(figure);
        departure.clear();
    }

    public Move createMove(Field departure, Field destination) {
        if (destination.isBusy()) {
            return new KillMove(departure, destination, departure.getFigure(), destination.getFigure());
        } else {
            return new DefaultMove(departure, destination, departure.getFigure());
        }
    }
}
