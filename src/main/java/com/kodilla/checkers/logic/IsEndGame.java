package com.kodilla.checkers.logic;

import com.kodilla.checkers.logic.moves.computersMove.ComputersPawns;
import com.kodilla.checkers.logic.moves.computersMove.NoOfPlayersPawns;

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
        List<Coordinates> computersPawns = new ComputersPawns(board, COMP_COLOUR).getComputersPawns();
        List<Coordinates> playersPawns = new NoOfPlayersPawns(board, PLAYERS_COLOUR).getPlayersPawns();
        int noOfComputerPawns = computersPawns.size();
        int noOfPlayersPawns = playersPawns.size();
        System.out.println(noOfComputerPawns + "computer left");
        System.out.println(noOfPlayersPawns + "player left");

        if ((row2 == 0 && board.getFigure(col2, row2).getColour().equals(PLAYERS_COLOUR)) || noOfComputerPawns == 0) {
            winner = "YOU! \n CONGRATULATIONS!";
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
