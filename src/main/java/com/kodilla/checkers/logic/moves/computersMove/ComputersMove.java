package com.kodilla.checkers.logic.moves.computersMove;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Coordinates;
import com.kodilla.checkers.logic.IsEndGame;

import java.util.*;

public class ComputersMove {
    private Board board;

    public ComputersMove(Board board) {
        this.board = board;
    }

    public Coordinates pickMove() {
        Optional<Coordinates> beatingCoordinate = new ComputersBeatingOptions(board).beatingOptions().stream()
                .flatMap(Collection::stream)
                .findAny();
        Optional<Coordinates> moveCoordinate = new ComputersMoveOptions(board).moveOptions().stream()
                .flatMap(Collection::stream)
                .findAny();

        try {
            return beatingCoordinate.orElse(moveCoordinate.get());
        }catch (NoSuchElementException e){
            return null;
        }
    }
}
