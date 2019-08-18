package com.kodilla.checkers.logic;

import com.kodilla.checkers.logic.moves.computersMove.ComputersMove;
import com.kodilla.checkers.logic.moves.NewFigure;
import com.kodilla.checkers.logic.moves.OldFigure;
import com.kodilla.checkers.logic.moves.playersMove.moves.PawnChoice;
import com.kodilla.checkers.logic.moves.playersMove.options.PossibleMovesDisplay;
import com.kodilla.checkers.logic.moves.playersMove.options.ToAddToGrid;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.*;

public class Board {
    private static final int BOARD_WIDTH = 835;
    private static final int BOARD_HIGHT = 835;
    private static final int FIELD_WIDTH = 100;
    private static final int FIELD_HIGHT = 100;
    private static final int PADDING = 35;
    private String PLAYERS_COLOUR;
    private String COMP_COLOUR;
    private List<BoardRow> rows = new ArrayList<>();
    private String winner;

    private GridPane grid;

    private ImagePattern blackPawnPattern;
    private ImagePattern whitePawnPattern;
    private ImagePattern PLAYERS_PATTERN;
    private ImagePattern COMP_PATTERN;

    private boolean endGame = false;

    public Board(GridPane grid, ImagePattern blackPawnPattern, ImagePattern whitePawnPattern) {
        this.grid = grid;
        this.blackPawnPattern = blackPawnPattern;
        this.whitePawnPattern = whitePawnPattern;

        for (int n = 0; n < 8; n++) {
            rows.add(new BoardRow());
        }
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setColours(String playersColour) {
        PLAYERS_COLOUR = playersColour;
        if (PLAYERS_COLOUR.equals("WHITE")) {
            COMP_COLOUR = "BLACK";
            COMP_PATTERN = blackPawnPattern;
            PLAYERS_PATTERN = whitePawnPattern;
        } else {
            COMP_COLOUR = "WHITE";
            COMP_PATTERN = whitePawnPattern;
            PLAYERS_PATTERN = blackPawnPattern;
        }
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().add(col, figure);
        rows.get(row).getCols().remove(col + 1);
    }

    public void initBoard(String playersColour) {
        setColours(playersColour);
        new StarterSet(this, COMP_COLOUR, PLAYERS_COLOUR).start();
    }

    public List<Node> move(int col1, int row1, int col2, int row2) {
        NewFigure f = new NewFigure(col1, row1, col2, row2, this);
        grid.add(f.newFigureToGrid(), col2, row2);
        setFigure(col2, row2, f.newFigure());

        setFigure(col1, row1, new None());
        OldFigure oldFigure = new OldFigure(col1, row1, col2, row2, this);

        if (row2 == 0) {
            endGame = true;
            winner = "YOU! \n CONGRATULATIONS!";
        }
        if (row2 == 7) {
            endGame = true;
            winner = "Computer player \n Maybe next time ;)";
        }

        return oldFigure.remove(grid);
    }

    public void showBoard() {
        grid.getChildren().clear();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (getFigure(col, row) instanceof Pawn) {
                    ImagePattern p = (getFigure(col, row).getColour().equals(COMP_COLOUR)) ? COMP_PATTERN : PLAYERS_PATTERN;
                    Circle systemPawn = new Circle();
                    systemPawn.setRadius(50);
                    systemPawn.setFill(p);
                    systemPawn.setStrokeWidth(50);
                    systemPawn.setId(col + "-" + row);
                    grid.add(systemPawn, col, row);
                }
            }
        }
    }

    public void showMoveOption(int col, int row) {
        PossibleMovesDisplay possibleMovesDisplay = new PossibleMovesDisplay(col, row, this);
        addToGrid(possibleMovesDisplay.display());
    }

    public void addToGrid(List<ToAddToGrid> toAddToGrid) {
        for (ToAddToGrid i : toAddToGrid) {
            if (i != null) {
                grid.add(i.getMoveOption(), i.getCol(), i.getRow());
            }
        }
    }

    public void enter(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;

        for (int x = PADDING; x < BOARD_WIDTH; x += FIELD_WIDTH) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + FIELD_WIDTH))) {
                for (int y = PADDING; y < BOARD_HIGHT; y += FIELD_HIGHT) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + FIELD_HIGHT))) {
                        System.out.println(col + "-" + row);
                        showMoveOption(col, row);
                    }
                    row++;
                }
            }
            col += 1;
        }
    }

    public List<Node> exit(MouseEvent mouseEvent) {
        List<Node> toRemove = new ArrayList<>();
        for (Node node : grid.getChildren()) {
            if (node instanceof Circle) {
                if ("highlight".equals(node.getId())) {
                    toRemove.add(node);
                }
            }
        }
        return toRemove;
    }

    private int[] moves;
    private List<Node> picked;

    public boolean startMove(MouseEvent mouseEvent) {
        PawnChoice p = new PawnChoice(this, grid);
        boolean startMoveDone = p.pawnChoice(mouseEvent);
        moves = p.getMoves();
        picked = p.getPicked();
        return startMoveDone;
    }

    public List<Node> endMove(MouseEvent mouseEvent) {

        int col = 0;
        int row = 0;
        boolean startMoveMethod = true;

        grid.getChildren().removeAll(picked);

        for (int x = PADDING; x < BOARD_WIDTH; x += FIELD_WIDTH) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = PADDING; y < BOARD_HIGHT; y += FIELD_HIGHT) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
                        if (!(getFigure(col, row) instanceof Pawn)) {
                            if (((moves[0] + 1) == col & (moves[1] - 1) == row)) {
                                moves[2] = col;
                                moves[3] = row;
                                System.out.println("col & row added on 2 & 3");
                            } else if (((moves[0] - 1) == col & (moves[1] - 1) == row)) {
                                moves[2] = col;
                                moves[3] = row;
                                System.out.println("col & row added on 2 & 3");
                            } else if (((moves[0] + 1) == col & (moves[1] + 1) == row)) {
                                System.out.println("You can't go back");
                                startMoveMethod = false;
                            } else if ((moves[0] - 1) == col & (moves[1] + 1) == row) {
                                System.out.println("You can't go back");
                                startMoveMethod = false;
                            } //beating down to the right:
                            else if (((moves[0] + 2) == col & (moves[1] + 2) == row)) {
                                if (getFigure(moves[0] + 1, moves[1] + 1) instanceof Pawn) {
                                    if (!(getFigure(moves[0], moves[1]).getColour().equals(getFigure(moves[0] + 1, moves[1] + 1).getColour()))) {
                                        moves[2] = col;
                                        moves[3] = row;
                                        System.out.println("col & row added on 2 & 3");
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            }//beating up to the right:
                            else if (((moves[0] + 2) == col & (moves[1] - 2) == row)) {
                                if (getFigure(moves[0] + 1, moves[1] - 1) instanceof Pawn) {
                                    if (!(getFigure(moves[0], moves[1]).getColour().equals(getFigure(moves[0] + 1, moves[1] - 1).getColour()))) {
                                        moves[2] = col;
                                        moves[3] = row;
                                        System.out.println("col & row added on 2 & 3");
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            }//beating up to the left:
                            else if (((moves[0] - 2) == col & (moves[1] - 2) == row)) {
                                if (getFigure(moves[0] - 1, moves[1] - 1) instanceof Pawn) {
                                    if (!(getFigure(moves[0], moves[1]).getColour().equals(getFigure(moves[0] - 1, moves[1] - 1).getColour()))) {
                                        moves[2] = col;
                                        moves[3] = row;
                                        System.out.println("col & row added on 2 & 3");
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            }//beating down to the left:
                            else if (((moves[0] - 2) == col & (moves[1] + 2) == row)) {
                                if (getFigure(moves[0] - 1, moves[1] + 1) instanceof Pawn) {
                                    if (!(getFigure(moves[0], moves[1]).getColour().equals(getFigure(moves[0] - 1, moves[1] + 1).getColour()))) {
                                        moves[2] = col;
                                        moves[3] = row;
                                        System.out.println("col & row added on 2 & 3");
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            } else {
                                System.out.println("Not allowed field");
                                startMoveMethod = false;
                            }
                        }//pick the same pawn again
                        else if (moves[0] == col & moves[1] == row) {
                            startMoveMethod = false;
                            System.out.println("This pawn is not picked anymore");
                        } else {
                            System.out.println(" You can't move onto another Pawn");
                            startMoveMethod = false;
                        }
                    }
                    row++;
                }
            }
            col += 1;
        }
        if (startMoveMethod) {
            return new ArrayList<>(move(moves[0], moves[1], moves[2], moves[3]));
        }
        return new ArrayList<>();
    }

    public List<Node> computersMove() {
        ComputersMove computersMove = new ComputersMove(this);
        Coordinates c = computersMove.pickMove();

        int col1 = c.getCol();
        int row1 = c.getRow();
        int col2 = c.getColToMove();
        int row2 = c.getRowToMove();
        System.out.println("Coordinates of computer's move after streams: " + col1 + " " + row1 + " " + col2 + " " + row2);

        return new ArrayList<>(move(col1, row1, col2, row2));
    }

    public boolean isEndGame() {
        return endGame;
    }

    public String getPlayersColour() {
        return PLAYERS_COLOUR;
    }

    public String getCOMP_COLOUR() {
        return COMP_COLOUR;
    }

    public ImagePattern getPLAYERS_PATTERN() {
        return PLAYERS_PATTERN;
    }

    public ImagePattern getCOMP_PATTERN() {
        return COMP_PATTERN;
    }

    public String getWinner() {
        return winner;
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static int getBoardHight() {
        return BOARD_HIGHT;
    }

    public static int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public static int getFieldHight() {
        return FIELD_HIGHT;
    }

    public static int getPADDING() {
        return PADDING;
    }
}


