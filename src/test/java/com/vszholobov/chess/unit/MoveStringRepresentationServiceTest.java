package com.vszholobov.chess.unit;

import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;
import com.vszholobov.chess.model.figure.FigureSide;
import com.vszholobov.chess.model.figure.FigureType;
import com.vszholobov.chess.model.move.DefaultMove;
import com.vszholobov.chess.model.move.KillMove;
import com.vszholobov.chess.model.move.Move;
import com.vszholobov.chess.service.MoveStringRepresentationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MoveStringRepresentationServiceTest {
    @InjectMocks
    private MoveStringRepresentationService moveStringRepresentationService;

    private static Field getDeparture() {
        return new Field(1, 1, new Figure(FigureType.KNIGHT, FigureSide.WHITE));
    }

    @Test
    void testKnightKillMoveStringRepresentation() {
        Field departure = getDeparture();
        Field destination = new Field(3, 2, new Figure(FigureType.KNIGHT, FigureSide.BLACK));
        Move move = new KillMove(departure, destination, departure.getFigure(), destination.getFigure());

        String stringRepresentation = moveStringRepresentationService.stringRepresentation(move);

        assertThat(stringRepresentation).isEqualTo("Kn a1 x b3");
    }

    @Test
    void testKnightDefaultMoveStringRepresentation() {
        Field departure = getDeparture();
        Field destination = new Field(3, 2, null);
        Move move = new DefaultMove(departure, destination, departure.getFigure());

        String stringRepresentation = moveStringRepresentationService.stringRepresentation(move);

        assertThat(stringRepresentation).isEqualTo("Kn a1 - b3");
    }
}
