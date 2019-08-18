package com.kodilla.checkers.logic;

public class Coordinates {
    int col;
    int row;
    int colToMove;
    int rowToMove;

    public Coordinates(int col, int row, int colToMove, int rowToMove) {
        this.col = col;
        this.row = row;
        this.colToMove = colToMove;
        this.rowToMove = rowToMove;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getColToMove() {
        return colToMove;
    }

    public int getRowToMove() {
        return rowToMove;
    }

    @Override
    public String toString() {
        return  "col=" + col +
                ", row=" + row +
                ", colToMove=" + colToMove +
                ", rowToMove=" + rowToMove +
                '}';
    }
}
