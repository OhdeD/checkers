package com.kodilla.checkers.logic.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;

import java.util.*;

public class ComputersMove {
    private Board board;
    private ComputersPawns computersPawns;

    public ComputersMove(Board board) {
        this.board = board;
        computersPawns = new ComputersPawns(board, board.getCOMP_COLOUR());
    }

    public Coordinates pickMove() {
        Optional<Coordinates> beatingCoordinate = new ComputersBeatingOptions(board).beatingOptions().stream()
                .flatMap(Collection::stream)
                .findAny();
        Optional<Coordinates> moveCoordinate = new ComputersMoveOptions(board).moveOptions().stream()
                .flatMap(Collection::stream)
                .findAny();

        Coordinates coordinates = beatingCoordinate.orElse(moveCoordinate.get());
        return coordinates;
    }
}
