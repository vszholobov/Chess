package com.testirovanie.lab4.board;

public class ChessBoard {
    private final Field[][] chessBoard = new Field[9][9];

    public void setField(Field field) {
        chessBoard[field.getRow() - 1][field.getCol() - 1] = field;
    }

    public Field getField(int row, int col) {
        return chessBoard[row - 1][col - 1];
    }
}
