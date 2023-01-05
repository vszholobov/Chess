package com.testirovanie.lab4.service;

import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.figure.ColumnLetter;
import com.testirovanie.lab4.model.figure.Figure;
import com.testirovanie.lab4.model.move.KillMove;
import com.testirovanie.lab4.model.move.Move;
import org.springframework.stereotype.Service;

@Service
public class MoveStringRepresentationService {
    public String stringRepresentation(Move move) {
        Figure figure = move.figure();
        Field destination = move.destination();
        Field departure = move.departure();
        String separator = move instanceof KillMove ? " x " : " - ";
        return figure.type().getChessName() + " " +
                ColumnLetter.fromCol(departure.getCol()) + departure.getRow() +
                separator +
                ColumnLetter.fromCol(destination.getCol()) + destination.getRow();
    }
}
