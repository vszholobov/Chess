package com.testirovanie.lab4.unit;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import com.testirovanie.lab4.figure.FigureSide;
import com.testirovanie.lab4.figure.FigureType;
import com.testirovanie.lab4.move.DefaultMove;
import com.testirovanie.lab4.move.KillMove;
import com.testirovanie.lab4.move.Move;
import com.testirovanie.lab4.move.MoveStringRepresentationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MoveStringRepresentationServiceTest {
    @InjectMocks
    private MoveStringRepresentationService moveStringRepresentationService;

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

    private static Field getDeparture() {
        return new Field(1, 1, new Figure(FigureType.KNIGHT, FigureSide.WHITE));
    }
}
