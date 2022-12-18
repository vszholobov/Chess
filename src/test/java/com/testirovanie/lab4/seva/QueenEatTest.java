package com.testirovanie.lab4.seva;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"knight.row=7", "knight.col=3"})
class QueenEatTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("blackQueen")
    private Figure blackQueen;

    @Test
    void testIsDeadFromStartWhiteKnight() {
        blackQueen.goToPosition(knight.getProperties().field());
        Field endQueenField = blackQueen.getProperties().field();
        assertThat(endQueenField.getFigure())
                .isEqualTo(blackQueen);
        assertThat(endQueenField.getRow())
                .isEqualTo(7);
        assertThat(endQueenField.getCol())
                .isEqualTo(3);
        assertThat(knight.getProperties().isDead()).isTrue();
    }
}
