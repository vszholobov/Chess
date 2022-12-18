package com.testirovanie.lab4.pasha;

import com.testirovanie.lab4.board.move.Move;
import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoveStringTest {
    @Autowired
    @Qualifier("whiteRightRook")
    private Figure whiteRightRook;

    @Test
    void testBoarderValidationKnightException() {
        Move tChessMove = whiteRightRook.goToPosition(new Field(2, 8, null));
        assertThat(tChessMove.stringRepresentation())
                .isEqualTo("R h1 - h2");
    }
}
