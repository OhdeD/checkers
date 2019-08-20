package com.kodilla.checkers.logic.moves.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;
import com.kodilla.checkers.logic.Pawn;

import java.util.ArrayList;
import java.util.List;

public class PawnsList {
    private Board board;
    private String colour;
    private List<Coordinates> pawns = new ArrayList<>();

    public PawnsList(Board board, String colour) {
        this.board = board;
        this.colour = colour;
    }

    public List<Coordinates> getPawns() {
        pawns.clear();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getFigure(col, row) instanceof Pawn) {
                    if (board.getFigure(col, row).getColour().equals(colour)) {
                        pawns.add(new Coordinates(col, row, 0, 0));
                    }
                }
            }
        }
        return pawns;
    }
}
