package com.kodilla.checkers.logic.moves.playersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Pawn;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovesDisplay {
    private int col;
    private int row;
    private Board board;
    private String colourOfPickedPawn;
    private BeatingOptionToGrid beating;
    private MoveOptionToGrid moving;
    private MoveOptionFigures c = new MoveOptionFigures();
    List<ToAddToGrid> toAddToGrid = new ArrayList<>();

    public PossibleMovesDisplay(int col, int row, Board board) {
        this.col = col;
        this.row = row;
        this.board = board;
        colourOfPickedPawn = board.getFigure(col, row).getColour();
        beating = new BeatingOptionToGrid(col, row, board);
        moving = new MoveOptionToGrid(col, row, board);
    }


    public List<ToAddToGrid> display() {

        if (board.getFigure(col, row) instanceof Pawn) {

            if (col == 7 & row == 7) {
                moving.moveUpToTheLeft(col, row, c.show(1));
                toAddToGrid.addAll(moving.getToAddToGrid());
                beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn);
                toAddToGrid.addAll(beating.getToAddToGrid());
            }
            if (col == 0 & row == 0) {
                beating.beatingDownToTheRight(col, row, c.show(1), colourOfPickedPawn);
                toAddToGrid.addAll(beating.getToAddToGrid());
            }
            if (col > 1 & col < 6 & row == 0) {
                beating.beatingDownToTheLeft(col, row, c.show(1), colourOfPickedPawn);
                beating.beatingDownToTheRight(col, row, c.show(2), colourOfPickedPawn);
                toAddToGrid.addAll(beating.getToAddToGrid());
            }
            if (col > 0 & col < 7 & row == 7) {
                moving.moveUpToTheLeft(col, row, c.show(1));
                moving.moveUpToTheRight(col, row, c.show(2));
                toAddToGrid.addAll(moving.getToAddToGrid());
                if (col > 1 & col < 6) {
                    beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn);
                    beating.beatingUpToTheRight(col, row, c.show(2), colourOfPickedPawn);
                    toAddToGrid.addAll(beating.getToAddToGrid());
                }
            }
            if (row > 0 & row < 7 & col == 0) {
                moving.moveUpToTheRight(col, row, c.show(1));
                toAddToGrid.addAll(moving.getToAddToGrid());
                if (row > 1 & row < 6) {
                    beating.beatingUpToTheRight(col, row, c.show(1), colourOfPickedPawn);
                    beating.beatingDownToTheRight(col, row, c.show(2), colourOfPickedPawn);
                    toAddToGrid.addAll(beating.getToAddToGrid());
                }
            }
            if (row > 0 & row < 7 & col == 7) {
                moving.moveUpToTheLeft(col, row, c.show(1));
                toAddToGrid.addAll(moving.getToAddToGrid());
                if (row > 1 & row < 6) {
                    beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn);
                    beating.beatingDownToTheLeft(col, row, c.show(2), colourOfPickedPawn);
                    toAddToGrid.addAll(beating.getToAddToGrid());
                }
            }
            if (row > 0 & row < 7 & col > 0 & col < 7) {
                moving.moveUpToTheLeft(col, row, c.show(1));
                moving.moveUpToTheRight(col, row, c.show(2));
                toAddToGrid.addAll(moving.getToAddToGrid());
                if (row > 1 & row < 6 & col > 1 & col < 6) {
                    beating.beatingUpToTheLeft(col, row, c.show(1), colourOfPickedPawn);
                    beating.beatingUpToTheRight(col, row, c.show(2), colourOfPickedPawn);
                    beating.beatingDownToTheLeft(col, row, c.show(3), colourOfPickedPawn);
                    beating.beatingDownToTheRight(col, row, c.show(0), colourOfPickedPawn);
                    toAddToGrid.addAll(beating.getToAddToGrid());
                }
            }
        }
        return toAddToGrid;
    }
}
