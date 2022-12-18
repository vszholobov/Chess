package com.testirovanie.lab4.pasha;

import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class RookMoveToAllyTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("whiteRightRook")
    private Figure whiteRightRook;

    @Test
    void testBoarderValidationKnightException() {
        assertThatThrownBy(() -> whiteRightRook.goToPosition(knight.getProperties().field()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("notAllyChessmanValidator");
    }
}
