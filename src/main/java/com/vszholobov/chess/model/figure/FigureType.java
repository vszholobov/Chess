package com.vszholobov.chess.model.figure;

import lombok.Getter;

@Getter
public enum FigureType {
    KNIGHT("Kn"),
    QUEEN("Q"),
    ROOK("R");

    private final String chessName;

    FigureType(String name) {
        this.chessName = name;
    }
}
