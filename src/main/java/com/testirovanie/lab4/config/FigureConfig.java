package com.testirovanie.lab4.config;

import com.testirovanie.lab4.model.figure.Figure;
import com.testirovanie.lab4.model.figure.FigureSide;
import com.testirovanie.lab4.model.figure.FigureType;
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
