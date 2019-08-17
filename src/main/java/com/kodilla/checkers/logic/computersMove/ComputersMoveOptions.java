package com.kodilla.checkers.logic.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;
import com.kodilla.checkers.logic.None;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ComputersMoveOptions {
    private Board board;
    private ComputersPawns computersPawns;

    public ComputersMoveOptions(Board board) {
        this.board = board;
        computersPawns = new ComputersPawns(board, board.getCOMP_COLOUR());
    }

    public Set<Set<Coordinates>> moveOptions() {

        Set<Coordinates> moveDownToTheRight = computersPawns.getComputersPawns().stream()
                .filter(e -> e.getCol() < 7 && e.getRow() < 7)
                .filter(e -> board.getFigure((e.getCol() + 1), (e.getRow() + 1)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() + 1, e.getRow() + 1))
                .collect(Collectors.toSet());
        Set<Coordinates> moveDownToTheLeft = computersPawns.getComputersPawns().stream()
                .filter(e -> e.getCol() > 0 && e.getRow() < 7)
                .filter(e -> board.getFigure((e.getCol() - 1), (e.getRow() + 1)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() - 1, e.getRow() + 1))
                .collect(Collectors.toSet());

        Set<Set<Coordinates>> moves = new HashSet<>();
        moves.add(moveDownToTheRight);
        moves.add(moveDownToTheLeft);

        return moves;
    }
}
