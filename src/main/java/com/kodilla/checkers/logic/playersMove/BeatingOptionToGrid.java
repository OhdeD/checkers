package com.kodilla.checkers.logic.playersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Pawn;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;


public class BeatingOptionToGrid {
    private int col;
    private int row;
    private Board board;
    private List<ToAddToGrid> toAddToGrid = new ArrayList<>();

    public BeatingOptionToGrid(int col, int row, Board board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    public void beatingUpToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if ((board.getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            if ((!board.getFigure((col + 1), (row - 1)).getColour().equals(colourOfPickedPawn))) {
                if (!(board.getFigure((col + 2), (row - 2)) instanceof Pawn)) {
                    toAddToGrid.add(new ToAddToGrid(moveOption2, col + 2, row - 2));
                }
            }
        }
    }

    public void beatingUpToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if ((board.getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            if (!board.getFigure((col - 1), (row - 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(board.getFigure((col - 2), (row - 2)) instanceof Pawn)) {
                    toAddToGrid.add(new ToAddToGrid(moveOption1, col - 2, row - 2));
                }
            }
        }
    }

    public void beatingDownToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if ((board.getFigure((col + 1), (row + 1)) instanceof Pawn)) {
            if (!board.getFigure((col + 1), (row + 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(board.getFigure((col + 2), (row + 2)) instanceof Pawn)) {
                    toAddToGrid.add(new ToAddToGrid(moveOption2, col + 2, row + 2));
                }
            }
        }
    }

    public void beatingDownToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if ((board.getFigure((col - 1), (row + 1)) instanceof Pawn)) {
            if (!board.getFigure((col - 1), (row + 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(board.getFigure((col - 2), (row + 2)) instanceof Pawn)) {
                    toAddToGrid.add(new ToAddToGrid(moveOption1, col - 2, row + 2));
                }
            }
        }
    }

    public List<ToAddToGrid> getToAddToGrid() {
        return toAddToGrid;
    }
}
