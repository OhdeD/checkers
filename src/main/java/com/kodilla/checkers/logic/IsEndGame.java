package com.kodilla.checkers.logic;

import com.kodilla.checkers.logic.moves.computersMove.PawnsList;

import java.util.List;

public class IsEndGame {
    private Board board;
    private String PLAYERS_COLOUR;
    private String COMP_COLOUR;
    private String winner;


    public IsEndGame(Board board) {
        this.board = board;
        PLAYERS_COLOUR = board.getPlayersColour();
        COMP_COLOUR = board.getCOMP_COLOUR();
    }

    public boolean isEnd(int col2, int row2) {
        List<Coordinates> computersPawns = new PawnsList(board, COMP_COLOUR).getPawns();
        List<Coordinates> playersPawns = new PawnsList(board, PLAYERS_COLOUR).getPawns();
        int noOfComputerPawns = computersPawns.size();
        int noOfPlayersPawns = playersPawns.size();
        System.out.println(noOfComputerPawns + "computer left");
        System.out.println(noOfPlayersPawns + "player left");

        if (row2 == 0 && board.getFigure(col2, row2) instanceof None && noOfComputerPawns == 0) {
            winner = "YOU! CONGRATULATIONS!";
            return true;
        } else if (row2 == 0 && board.getFigure(col2, row2).getColour().equals(PLAYERS_COLOUR)) {
            winner = "YOU! CONGRATULATIONS!";
            return true;
        }
        if ((row2 == 7 && board.getFigure(col2, row2).getColour().equals(COMP_COLOUR)) || noOfPlayersPawns == 0) {
            winner = "Computer player \n Maybe next time ;)";
            return true;
        }
        return false;
    }

    public String getWinner() {
        return winner;
    }
}
