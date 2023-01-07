package com.vszholobov.chess.config;

import com.vszholobov.chess.model.figure.Figure;
import com.vszholobov.chess.model.figure.FigureSide;
import com.vszholobov.chess.model.figure.FigureType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FigureConfig {
    @Bean
    public Figure whiteKnight() {
        return new Figure(FigureType.KNIGHT, FigureSide.WHITE);
    }

    @Bean
    public Figure whiteRightRook() {
        return new Figure(FigureType.ROOK, FigureSide.WHITE);
    }

    @Bean
    public Figure blackQueen() {
        return new Figure(FigureType.QUEEN, FigureSide.BLACK);
    }
}
