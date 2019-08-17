package com.kodilla.checkers.logic;

import javafx.scene.shape.Circle;



public class BeatingOptionToGrid {
    int col;
    int row;
    Board board;

    public BeatingOptionToGrid(int col, int row, Board board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    public ToAddToGrid beatingUpToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if ((board.getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            if ((!board.getFigure((col + 1), (row - 1)).getColour().equals(colourOfPickedPawn))) {
                if (!(board.getFigure((col + 2), (row - 2)) instanceof Pawn)) {
                    return new ToAddToGrid(moveOption2, col + 2, row - 2);
                }
            }
        }
        return null;
    }
    public ToAddToGrid beatingUpToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if ((board.getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            if (!board.getFigure((col - 1), (row - 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(board.getFigure((col - 2), (row - 2)) instanceof Pawn)) {
                    new ToAddToGrid(moveOption1, col - 2, row - 2);
                }
            }
        }

        return null;
    }
    public ToAddToGrid beatingDownToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if ((board.getFigure((col + 1), (row + 1)) instanceof Pawn)) {
            if (!board.getFigure((col + 1), (row + 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(board.getFigure((col + 2), (row + 2)) instanceof Pawn)) {
                    new ToAddToGrid(moveOption2, col + 2, row + 2);
                }
            }
        }
        return null;
    }
  public ToAddToGrid beatingDownToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if ((board.getFigure((col - 1), (row + 1)) instanceof Pawn)) {
            if (!board.getFigure((col - 1), (row + 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(board.getFigure((col - 2), (row + 2)) instanceof Pawn)) {
                    new ToAddToGrid(moveOption1, col - 2, row + 2);
                }
            }
        }
      return null;
    }
}
