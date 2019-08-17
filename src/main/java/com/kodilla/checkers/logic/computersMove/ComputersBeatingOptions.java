package com.kodilla.checkers.logic.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;
import com.kodilla.checkers.logic.None;
import com.kodilla.checkers.logic.Pawn;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ComputersBeatingOptions {
    private Board board;
    private ComputersPawns computersPawns;

    public ComputersBeatingOptions(Board board) {
        this.board = board;
        computersPawns = new ComputersPawns(board, board.getCOMP_COLOUR());
    }
    public Set<Set<Coordinates>> beatingOptions() {
        Set<Coordinates> beatingDownToTheRight = computersPawns.getComputersPawns().stream()
                .filter(e -> e.getCol() < 6 && e.getRow() < 6)
                .filter(e -> board.getFigure((e.getCol() + 1), (e.getRow() + 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() + 1), (e.getRow() + 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() + 2), (e.getRow() + 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() + 2, e.getRow() + 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingDownToTheLeft = computersPawns.getComputersPawns().stream()
                .filter(e -> e.getCol() > 1 && e.getRow() < 6)
                .filter(e -> board.getFigure((e.getCol() - 1), (e.getRow() + 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() - 1), (e.getRow() + 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() - 2), (e.getRow() + 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() - 2, e.getRow() + 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingUpToTheRight = computersPawns.getComputersPawns().stream()
                .filter(e -> e.getCol() < 6 && e.getRow() > 1)
                .filter(e -> board.getFigure((e.getCol() + 1), (e.getRow() - 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() + 1), (e.getRow() - 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() + 2), (e.getRow() - 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getColToMove() + 2, e.getRowToMove() - 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingUpToTheLeft = computersPawns.getComputersPawns().stream()
                .filter(e -> e.getCol() > 1 && e.getRow() > 1)
                .filter(e -> board.getFigure((e.getCol() - 1), (e.getRow() - 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() - 1), (e.getRow() - 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() - 2), (e.getRow() - 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getColToMove() - 2, e.getRowToMove() - 2))
                .collect(Collectors.toSet());

        Set<Set<Coordinates>> beatings = new HashSet<>();
        beatings.add(beatingUpToTheRight);
        beatings.add(beatingUpToTheLeft);
        beatings.add(beatingDownToTheRight);
        beatings.add(beatingDownToTheLeft);

        return beatings;
    }
}
