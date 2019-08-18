package com.kodilla.checkers.logic.moves;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Figure;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class NewFigure {
    private int col1;
    private int row1;
    private int col2;
    private int row2;
    private Board board;
    private Figure f;
    private ImagePattern p;

    public NewFigure(int col1, int row1, int col2, int row2, Board board) {
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.board = board;

        f = board.getFigure(col1, row1);
        p = (board.getPlayersColour().equals(f.getColour())) ? board.getCOMP_PATTERN() : board.getPLAYERS_PATTERN();
    }

    public Circle newFigureToGrid() {
        Circle systemPawn = new Circle();
        systemPawn.setRadius(50);
        systemPawn.setFill(p);
        systemPawn.setStrokeWidth(50);
        systemPawn.setId(col2 + "-" + row2);
        return systemPawn;
    }

    public Figure newFigure() {
        return f;
    }
}
