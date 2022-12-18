package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import org.springframework.stereotype.Component;

@Component
public class MoveFactory {
    public Move createMove(Field departure, Field destination) {
        if (destination.isBusy()) {
            return new KillMove(departure, destination, departure.getFigure(), destination.getFigure());
        } else {
            return new NoKillMove(departure, destination, departure.getFigure());
        }
    }
}
