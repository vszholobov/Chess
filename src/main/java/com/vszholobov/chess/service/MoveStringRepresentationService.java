package com.vszholobov.chess.service;

import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.ColumnLetter;
import com.vszholobov.chess.model.figure.Figure;
import com.vszholobov.chess.model.move.KillMove;
import com.vszholobov.chess.model.move.Move;
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
