package com.testirovanie.lab4.figure;

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

    public static FigureType fromName(String chessName) {
        for (FigureType value : FigureType.values()) {
            if (value.getChessName().equals(chessName)) {
                return value;
            }
        }
        return null;
    }
}
