package com.vszholobov.chess.model.figure;

import lombok.Getter;

@Getter
public enum ColumnLetter {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);
    private final int num;

    ColumnLetter(int num) {
        this.num = num;
    }

    public static ColumnLetter fromCol(int col) {
        for (ColumnLetter value : ColumnLetter.values()) {
            if (value.getNum() == col) {
                return value;
            }
        }
        return null;
    }
}
