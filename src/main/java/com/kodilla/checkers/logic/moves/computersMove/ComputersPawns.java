package com.kodilla.checkers.logic.moves.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;
import com.kodilla.checkers.logic.Pawn;

import java.util.ArrayList;
import java.util.List;

public class ComputersPawns {
    private Board board;
    private String COMP_COLOUR;
    private List<Coordinates> computersPawns = new ArrayList<>();

    public ComputersPawns(Board board, String COMP_COLOUR) {
        this.board = board;
        this.COMP_COLOUR = COMP_COLOUR;
    }

    public List<Coordinates> getComputersPawns() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getFigure(col, row) instanceof Pawn) {
                    if (board.getFigure(col, row).getColour().equals(COMP_COLOUR)) {
                        computersPawns.add(new Coordinates(col, row, 0, 0));
                    }
                }
            }
        }
        return computersPawns;
    }
}
