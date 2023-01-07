package com.vszholobov.chess.config;

import com.vszholobov.chess.model.board.ChessBoard;
import com.vszholobov.chess.model.board.Field;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardConfig {
    @Bean
    public ChessBoard chessboard() {
        ChessBoard chessboard = new ChessBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard.setField(new Field(i + 1, j + 1, null));
            }
        }
        return chessboard;
    }
}
