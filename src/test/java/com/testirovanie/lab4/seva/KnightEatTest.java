package com.testirovanie.lab4.seva;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = { "knight.row=6", "knight.col=3" })
class KnightEatTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("blackQueen")
    private Figure blackQueen;

    @Test
    void testIsDeadFromStartWhiteKnight() {
        knight.goToPosition(blackQueen.getProperties().field());
        Field endKnightField = knight.getProperties().field();
        assertThat(endKnightField.getFigure())
                .isEqualTo(knight);
        assertThat(endKnightField.getRow())
                .isEqualTo(8);
        assertThat(endKnightField.getCol())
                .isEqualTo(4);
        assertThat(blackQueen.getProperties().isDead()).isTrue();
    }
}
