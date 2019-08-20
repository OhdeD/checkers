package com.kodilla.checkers.logic.moves.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;
import com.kodilla.checkers.logic.None;
import com.kodilla.checkers.logic.Pawn;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ComputersBeatingOptions {
    private Board board;
    private PawnsList pawnsList;

    public ComputersBeatingOptions(Board board) {
        this.board = board;
        pawnsList = new PawnsList(board, board.getCOMP_COLOUR());
    }

    public Set<Set<Coordinates>> beatingOptions() {
        Set<Coordinates> beatingDownToTheRight = pawnsList.getPawns().stream()
                .filter(e -> e.getCol() < 6 && e.getRow() < 6)
                .filter(e -> board.getFigure((e.getCol() + 1), (e.getRow() + 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() + 1), (e.getRow() + 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() + 2), (e.getRow() + 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() + 2, e.getRow() + 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingDownToTheLeft = pawnsList.getPawns().stream()
                .filter(e -> e.getCol() > 1 && e.getRow() < 6)
                .filter(e -> board.getFigure((e.getCol() - 1), (e.getRow() + 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() - 1), (e.getRow() + 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() - 2), (e.getRow() + 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() - 2, e.getRow() + 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingUpToTheRight = pawnsList.getPawns().stream()
                .filter(e -> e.getCol() < 6 && e.getRow() > 1)
                .filter(e -> board.getFigure((e.getCol() + 1), (e.getRow() - 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() + 1), (e.getRow() - 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() + 2), (e.getRow() - 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() + 2, e.getRow() - 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingUpToTheLeft = pawnsList.getPawns().stream()
                .filter(e -> e.getCol() > 1 && e.getRow() > 1)
                .filter(e -> board.getFigure((e.getCol() - 1), (e.getRow() - 1)) instanceof Pawn)
                .filter(e -> !board.getFigure((e.getCol() - 1), (e.getRow() - 1)).getColour().equals(board.getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> board.getFigure((e.getCol() - 2), (e.getRow() - 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() - 2, e.getRow() - 2))
                .collect(Collectors.toSet());

        Set<Set<Coordinates>> beatings = new HashSet<>();
        beatings.add(beatingUpToTheRight);
        beatings.add(beatingUpToTheLeft);
        beatings.add(beatingDownToTheRight);
        beatings.add(beatingDownToTheLeft);
        System.out.println("mozliwosci bicia: " + (beatingDownToTheLeft.size()+beatingDownToTheRight.size() + beatingUpToTheLeft.size() + beatingUpToTheRight.size()));

        return beatings;
}
}
