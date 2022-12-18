package com.testirovanie.lab4.pasha;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = {"knight.row=1", "knight.col=1"})
class KnightWrongMoveTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Test
    void testBoarderValidationKnightException() {
        assertThatThrownBy(() -> knight.goToPosition(new Field(3, 1, null)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("knightMoveValidator");
    }
}
