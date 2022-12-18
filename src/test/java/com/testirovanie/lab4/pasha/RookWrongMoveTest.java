package com.testirovanie.lab4.pasha;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class RookWrongMoveTest {
    @Autowired
    @Qualifier("whiteRightRook")
    private Figure whiteRightRook;

    @Test
    void testBoarderValidationKnightException() {
        assertThatThrownBy(() -> whiteRightRook.goToPosition(new Field(7, 7, null)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("rookMoveValidator");
    }
}
