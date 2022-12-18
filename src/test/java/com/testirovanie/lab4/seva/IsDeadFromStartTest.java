package com.testirovanie.lab4.seva;

import com.testirovanie.lab4.figure.Figure;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
class IsDeadFromStartTest {
    @Autowired
    @Qualifier("whiteKnight")
    private Figure knight;

    @Autowired
    @Qualifier("blackQueen")
    private Figure blackQueen;

    @Test
    void testIsDeadFromStartWhiteKnight() {
        assertThat(knight.getProperties().isDead()).isFalse();
    }

    @Test
    void testIsDeadFromStartBlackQueen() {
        assertThat(blackQueen.getProperties().isDead()).isFalse();
    }
}
