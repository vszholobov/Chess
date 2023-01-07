package com.vszholobov.chess.integration;

import com.vszholobov.chess.model.board.ChessBoard;
import com.vszholobov.chess.model.board.Field;
import com.vszholobov.chess.model.figure.Figure;
import com.vszholobov.chess.model.figure.FigureSide;
import com.vszholobov.chess.model.figure.FigureType;
import com.vszholobov.chess.service.MoveService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@SpringBootTest()
class FigureOnPathTest {
    public static final int KNIGHT_ROW = 4;
    public static final int KNIGHT_COL = 4;
    @MockBean
    private ChessBoard chessboard;
    @Autowired
    private MoveService moveService;

    private static Stream<Arguments> wrongKnightMoves() {
        return IntStream.range(1, 9)
                .boxed()
                .flatMap(row -> IntStream.range(1, 9).mapToObj(col -> new Field(row, col, null)))
                .filter(f -> (Math.abs(KNIGHT_COL - f.getCol()) != 2 || Math.abs(f.getRow() - KNIGHT_ROW) != 1) &&
                        (Math.abs(KNIGHT_COL - f.getCol()) != 1 || Math.abs(f.getRow() - KNIGHT_ROW) != 2))
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("wrongKnightMoves")
    void knightMoveValidatorTest(Field destination) {
        Figure whiteKnight = new Figure(FigureType.KNIGHT, FigureSide.WHITE);
        Field departure = new Field(KNIGHT_ROW, KNIGHT_COL, whiteKnight);

        assertThatThrownBy(() -> moveService.move(departure, destination))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("knightMoveValidator");
    }

    @Test
    void notAllyChessmanValidatorTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Figure whiteKnight = new Figure(FigureType.KNIGHT, FigureSide.WHITE);
        Field departure = new Field(1, 2, whiteRook);
        Field destination = new Field(1, 3, whiteKnight);

        assertThatThrownBy(() -> moveService.move(departure, destination))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("notAllyChessmanValidator");
    }

    @Test
    void departureEqualsDestinationTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Field departure = new Field(1, 2, whiteRook);

        assertThatThrownBy(() -> moveService.move(departure, departure))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("departureEqualsDestinationValidator");
    }

    @Test
    void boardersBreachTest() {
        Figure whiteRook = new Figure(FigureType.ROOK, FigureSide.WHITE);
        Field departure = new Field(1, 2, whiteRook);
        Field destination = new Field(100, 100, null);

        assertThatThrownBy(() -> moveService.move(departure, destination))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("boardersBreachValidator");
    }

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
