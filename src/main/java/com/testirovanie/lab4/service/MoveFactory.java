package com.testirovanie.lab4.service;

import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.move.DefaultMove;
import com.testirovanie.lab4.model.move.KillMove;
import com.testirovanie.lab4.model.move.Move;
import org.springframework.stereotype.Service;

@Service
public class MoveFactory {
    public Move createMove(Field departure, Field destination) {
        if (destination.isBusy()) {
            return new KillMove(departure, destination, departure.getFigure(), destination.getFigure());
        } else {
            return new DefaultMove(departure, destination, departure.getFigure());
        }
    }
}
