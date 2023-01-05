package com.testirovanie.lab4.integration;

import com.testirovanie.lab4.service.MoveFigureService;
import com.testirovanie.lab4.model.board.ChessBoard;
import com.testirovanie.lab4.model.board.Field;
import com.testirovanie.lab4.model.figure.Figure;
import com.testirovanie.lab4.model.figure.FigureSide;
import com.testirovanie.lab4.model.figure.FigureType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@SpringBootTest()
class FigureOnPathTest {
    @MockBean
    private ChessBoard chessboard;
    @Autowired
    private MoveFigureService moveFigureService;

    @Test
    void figureOnPathTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Field departure = new Field(1, 3, whiteRook);
        Field destination = new Field(1, 1, null);

        given(chessboard.getField(1, 2))
                .willReturn(new Field(1, 2, new Figure(FigureType.KNIGHT, FigureSide.WHITE)));

        assertThatThrownBy(() -> moveFigureService.move(departure, destination))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("linePathValidator");
    }

    @Test
    void whiteRookEatBlackKnightTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Figure blackKnight = new Figure(FigureType.KNIGHT, FigureSide.BLACK);
        Field departure = new Field(1, 2, whiteRook);
        Field destination = new Field(1, 1, blackKnight);

        moveFigureService.move(departure, destination);

        assertThat(departure.getFigure()).isNull();
        assertThat(destination.getFigure()).isEqualTo(whiteRook);
    }
}
