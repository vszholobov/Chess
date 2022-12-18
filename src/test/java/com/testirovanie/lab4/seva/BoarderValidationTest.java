package com.testirovanie.lab4.seva;

import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest()
class BoarderValidationTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("blackQueen")
    private Figure blackQueen;

    @Test
    void testBoarderValidationKnightException() {
        assertThatThrownBy(() -> knight.goToPosition(new Field(99, 99, null)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("boardersBreachValidator");
    }

    @Test
    void testBoarderValidationQueenException() {
        assertThatThrownBy(() -> blackQueen.goToPosition(new Field(99, 99, null)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("boardersBreachValidator");
    }
}
