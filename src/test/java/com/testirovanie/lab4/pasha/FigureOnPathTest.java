package com.testirovanie.lab4.pasha;

import com.testirovanie.lab4.board.ChessBoard;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest()
class FigureOnPathTest {
    @Autowired
    private ChessBoard chessboard;

    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("whiteRightRook")
    private Figure whiteRightRook;

    @Test
    void figureOnPathTest() {
        assertThatThrownBy(() -> whiteRightRook.goToPosition(chessboard.getField(1, 1)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("linePathValidator");
    }
}
