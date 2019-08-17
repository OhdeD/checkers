package com.kodilla.checkers.logic;

import javafx.scene.shape.Circle;

public class MoveOptionToGrid {
    int col;
    int row;
    Board board;

    public MoveOptionToGrid(int col, int row, Board board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    public ToAddToGrid moveUpToTheRight(int col, int row, Circle moveOption2) {
        if (!(board.getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            return new ToAddToGrid(moveOption2, col + 1, row - 1);
        }
        return null;
    }


    public ToAddToGrid moveUpToTheLeft(int col, int row, Circle moveOption1) {
        if (!(board.getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            return new ToAddToGrid(moveOption1, col - 1, row - 1);
        }
        return null;
    }

}
