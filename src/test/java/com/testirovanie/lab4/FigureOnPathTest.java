package com.testirovanie.lab4;

import com.testirovanie.lab4.board.ChessBoard;
import com.testirovanie.lab4.board.Field;
import com.testirovanie.lab4.figure.Figure;
import com.testirovanie.lab4.figure.FigureSide;
import com.testirovanie.lab4.figure.FigureType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest()
class FigureOnPathTest {
    @MockBean
    private ChessBoard chessboard;
    @Autowired
    private MoveFigureService moveFigureService;

    @Test
    void figureOnPathTest() {
        Field departure = new Field(1, 8, new Figure(FigureType.ROOK, FigureSide.WHITE));
        Field destination = new Field(1, 1, null);

        Mockito.when(chessboard.getField(1, 2))
                .thenReturn(new Field(1, 2, new Figure(FigureType.KNIGHT, FigureSide.WHITE)));

        assertThatThrownBy(() -> moveFigureService.move(departure, destination))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("linePathValidator");
    }
}
