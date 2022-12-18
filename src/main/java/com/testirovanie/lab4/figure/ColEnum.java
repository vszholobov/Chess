package com.testirovanie.lab4.figure;

import lombok.Getter;

@Getter
public enum ColEnum {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);
    private final int num;

    ColEnum(int num) {
        this.num = num;
    }

    public static ColEnum fromCol(int col) {
        for (ColEnum value : ColEnum.values()) {
            if (value.getNum() == col) {
                return value;
            }
        }
        return null;
    }
}
