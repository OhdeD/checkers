package com.kodilla.checkers.logic.moves.playersMove;

import javafx.scene.shape.Circle;

public class ToAddToGrid {
    Circle moveOption;
    int col;
    int row;

    public ToAddToGrid(Circle moveOption, int col, int row) {
        this.moveOption = moveOption;
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Circle getMoveOption() {
        return moveOption;
    }

}
