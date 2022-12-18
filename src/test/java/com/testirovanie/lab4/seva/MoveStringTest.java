package com.testirovanie.lab4.seva;

import com.testirovanie.lab4.board.move.Move;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"knight.row=6", "knight.col=5"})
class MoveStringTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("blackQueen")
    private Figure blackQueen;

    @Test
    void testIsDeadFromStartWhiteKnight() {
        Move move = knight.goToPosition(blackQueen.getProperties().field());
        assertThat(move.stringRepresentation())
                .isEqualTo("Kn e6 x d8");
    }
}
