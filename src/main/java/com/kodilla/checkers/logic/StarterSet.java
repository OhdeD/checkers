package com.kodilla.checkers.logic;

public class StarterSet {
    private Board board;
    private String COMP_COLOUR;
    private String PLAYERS_COLOUR;

    public StarterSet(Board board, String COMP_COLOUR, String PLAYERS_COLOUR) {
        this.board = board;
        this.COMP_COLOUR = COMP_COLOUR;
        this.PLAYERS_COLOUR = PLAYERS_COLOUR;
    }

    public void start() {
        board.setFigure(0, 0, new Pawn(COMP_COLOUR));
        board.setFigure(2, 0, new Pawn(COMP_COLOUR));
        board.setFigure(4, 0, new Pawn(COMP_COLOUR));
        board.setFigure(6, 0, new Pawn(COMP_COLOUR));
        board.setFigure(1, 1, new Pawn(COMP_COLOUR));
        board.setFigure(3, 1, new Pawn(COMP_COLOUR));
        board.setFigure(5, 1, new Pawn(COMP_COLOUR));
        board.setFigure(7, 1, new Pawn(COMP_COLOUR));
        board.setFigure(0, 2, new Pawn(COMP_COLOUR));
        board.setFigure(2, 2, new Pawn(COMP_COLOUR));
        board.setFigure(4, 2, new Pawn(COMP_COLOUR));
        board.setFigure(6, 2, new Pawn(COMP_COLOUR));

        board.setFigure(1, 5, new Pawn(PLAYERS_COLOUR));
        board.setFigure(3, 5, new Pawn(PLAYERS_COLOUR));
        board.setFigure(5, 5, new Pawn(PLAYERS_COLOUR));
        board.setFigure(7, 5, new Pawn(PLAYERS_COLOUR));
        board.setFigure(0, 6, new Pawn(PLAYERS_COLOUR));
        board.setFigure(2, 6, new Pawn(PLAYERS_COLOUR));
        board.setFigure(4, 6, new Pawn(PLAYERS_COLOUR));
        board.setFigure(6, 6, new Pawn(PLAYERS_COLOUR));
        board.setFigure(1, 7, new Pawn(PLAYERS_COLOUR));
        board.setFigure(3, 7, new Pawn(PLAYERS_COLOUR));
        board.setFigure(5, 7, new Pawn(PLAYERS_COLOUR));
        board.setFigure(7, 7, new Pawn(PLAYERS_COLOUR));
    }
}
