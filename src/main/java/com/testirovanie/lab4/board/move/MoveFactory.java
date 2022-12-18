package com.testirovanie.lab4.board.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.FigureProperties;
import org.springframework.stereotype.Component;

@Component
public class MoveFactory {
    public Move createMove(Field destination, FigureProperties figureProperties) {
        if (destination.isBusy()) {
            return new KillMove(destination, figureProperties, destination.getFigure().getProperties());
        } else {
            return new NoKillMove(destination, figureProperties);
        }
    }
}
