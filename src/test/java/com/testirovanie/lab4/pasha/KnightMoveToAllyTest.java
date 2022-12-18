package com.testirovanie.lab4.pasha;

import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = {"knight.row=3", "knight.col=7"})
class KnightMoveToAllyTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("whiteRightRook")
    private Figure whiteRightRook;

    @Test
    void testBoarderValidationKnightException() {
        assertThatThrownBy(() -> knight.goToPosition(whiteRightRook.getProperties().field()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("notAllyChessmanValidator");
    }
}
