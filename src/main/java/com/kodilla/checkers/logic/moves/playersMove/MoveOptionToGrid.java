package com.kodilla.checkers.logic.moves.playersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Pawn;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class MoveOptionToGrid {
   private int col;
    private int row;
    private Board board;
    private List<ToAddToGrid> toAddToGrid = new ArrayList<>();

    public MoveOptionToGrid(int col, int row, Board board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    public void moveUpToTheRight(int col, int row, Circle moveOption2) {
        if (!(board.getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            toAddToGrid.add(new ToAddToGrid(moveOption2, col + 1, row - 1));
        }
    }


    public void moveUpToTheLeft(int col, int row, Circle moveOption1) {
        if (!(board.getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            toAddToGrid.add(new ToAddToGrid(moveOption1, col - 1, row - 1));
        }
    }

    public List<ToAddToGrid> getToAddToGrid() {
        return toAddToGrid;
    }
}
