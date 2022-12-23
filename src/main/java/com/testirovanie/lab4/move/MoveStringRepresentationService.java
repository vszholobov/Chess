package com.testirovanie.lab4.move;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.ColumnLetter;
import com.testirovanie.lab4.figure.Figure;
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
