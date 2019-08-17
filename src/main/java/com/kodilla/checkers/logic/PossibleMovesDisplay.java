package com.kodilla.checkers.logic;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovesDisplay {
    private int col;
    private int row;
    private Board board;

    public PossibleMovesDisplay(int col, int row, Board board) {
        this.col = col;
        this.row = row;
        this.board = board;
    }

    private String colourOfPickedPawn = board.getFigure(col, row).getColour();
    private MoveOptionFigures c = new MoveOptionFigures();
    private BeatingOptionToGrid beating = new BeatingOptionToGrid(col, row, board);
    private MoveOptionToGrid moving = new MoveOptionToGrid(col, row, board);

    public List<ToAddToGrid> display() {
        List<ToAddToGrid> toAddToGrid = new ArrayList<>();

        if (board.getFigure(col, row) instanceof Pawn) {

            if (col == 7 & row == 7) {
                toAddToGrid.add(moving.moveUpToTheLeft(col, row, c.show(1)));
                toAddToGrid.add(beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn));
            }
            if (col == 0 & row == 0) {
                toAddToGrid.add(beating.beatingDownToTheRight(col, row, c.show(1), colourOfPickedPawn));
            }
            if (col > 1 & col < 6 & row == 0) {
                toAddToGrid.add(beating.beatingDownToTheLeft(col, row, c.show(1), colourOfPickedPawn));
                toAddToGrid.add(beating.beatingDownToTheRight(col, row, c.show(2), colourOfPickedPawn));
            }
            if (col > 0 & col < 7 & row == 7) {
                toAddToGrid.add(moving.moveUpToTheLeft(col, row, c.show(1)));
                toAddToGrid.add(moving.moveUpToTheRight(col, row, c.show(2)));
                if (col > 1 & col < 6) {
                    toAddToGrid.add(beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn));
                    toAddToGrid.add(beating.beatingUpToTheRight(col, row, c.show(2), colourOfPickedPawn));
                }
            }
            if (row > 0 & row < 7 & col == 0) {
                toAddToGrid.add(moving.moveUpToTheRight(col, row, c.show(1)));
                if (row > 1 & row < 6) {
                    toAddToGrid.add(beating.beatingUpToTheRight(col, row, c.show(1), colourOfPickedPawn));
                    toAddToGrid.add(beating.beatingDownToTheRight(col, row, c.show(2), colourOfPickedPawn));
                }
            }
            if (row > 0 & row < 7 & col == 7) {
                toAddToGrid.add(moving.moveUpToTheLeft(col, row, c.show(1)));
                if (row > 1 & row < 6) {
                    toAddToGrid.add(beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn));
                    toAddToGrid.add(beating.beatingDownToTheLeft(col, row, c.show(2), colourOfPickedPawn));
                }
            }
            if (row > 0 & row < 7 & col > 0 & col < 7) {
                toAddToGrid.add(moving.moveUpToTheLeft(col, row, c.show(1)));
                toAddToGrid.add(moving.moveUpToTheRight(col, row, c.show(2)));
                if (row > 1 & row < 6 & col > 1 & col < 6) {
                    toAddToGrid.add(beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn));
                    toAddToGrid.add(beating.beatingDownToTheLeft(col, row, c.show(2), colourOfPickedPawn));
                    toAddToGrid.add(beating.beatingUpToTheRight(col, row, c.show(3), colourOfPickedPawn));
                    toAddToGrid.add(beating.beatingDownToTheRight(col, row, c.show(0), colourOfPickedPawn));
                }
            }
        }
        return toAddToGrid;
    }

}
