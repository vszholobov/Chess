package com.vszholobov.chess.integration;

import com.vszholobov.chess.service.MoveService;
import com.vszholobov.chess.model.board.ChessBoard;
import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;
import com.vszholobov.chess.model.figure.FigureSide;
import com.vszholobov.chess.model.figure.FigureType;
import org.assertj.core.api.Assertions;
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
    private MoveService moveService;

    @Test
    void figureOnPathTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Field departure = new Field(1, 3, whiteRook);
        Field destination = new Field(1, 1, null);

        given(chessboard.getField(1, 2))
                .willReturn(new Field(1, 2, new Figure(FigureType.KNIGHT, FigureSide.WHITE)));

        assertThatThrownBy(() -> moveService.move(departure, destination))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("linePathValidator");
    }

    @Test
    void whiteRookEatBlackKnightTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Figure blackKnight = new Figure(FigureType.KNIGHT, FigureSide.BLACK);
        Field departure = new Field(1, 2, whiteRook);
        Field destination = new Field(1, 1, blackKnight);

        moveService.move(departure, destination);

        Assertions.assertThat(departure.getFigure()).isNull();
        Assertions.assertThat(destination.getFigure()).isEqualTo(whiteRook);
    }
}
