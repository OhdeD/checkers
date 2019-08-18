package com.kodilla.checkers.logic.moves.playersMove.moves;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Pawn;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static com.kodilla.checkers.logic.Board.*;

public class PawnMove {
    private Board board;
    private GridPane grid;
    private List<Node> picked;
    private int BOARD_WIDTH;
    private int BOARD_HIGHT;
    private int FIELD_WIDTH;
    private int FIELD_HIGHT;
    private int PADDING;
    private int[] moves;
    private int col;
    private int row;
    private String pawnToMoveColour;


    public PawnMove(Board board, GridPane grid, List<Node> picked) {
        this.board = board;
        this.grid = grid;
        this.picked = picked;
        BOARD_HIGHT = getBoardHight();
        BOARD_WIDTH = getBoardWidth();
        FIELD_HIGHT = getFieldHight();
        FIELD_WIDTH = getFieldWidth();
        PADDING = getPADDING();
        moves = board.getMoves();
        pawnToMoveColour = board.getFigure(moves[0], moves[1]).getColour();
    }

    public List<Node> moveExecutor(MouseEvent mouseEvent) {
        boolean startMoveMethod = true;

        grid.getChildren().removeAll(picked);

        for (int x = PADDING; x < BOARD_WIDTH; x += FIELD_WIDTH) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = PADDING; y < BOARD_HIGHT; y += FIELD_HIGHT) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
                        if (!(board.getFigure(col, row) instanceof Pawn)) {
                            if (upRight()) {
                                addCoordinates();
                            } else if (upLeft()) {
                                addCoordinates();
                            } else if (downLeft() || downRight()) {
                                System.out.println("You can't go back");
                                startMoveMethod = false;
                            } else if (downRightBeating()) {
                                if (board.getFigure(moves[0] + 1, moves[1] + 1) instanceof Pawn) {
                                    if (!(board.getFigure(moves[0], moves[1]).getColour().equals(board.getFigure(moves[0] + 1, moves[1] + 1).getColour()))) {
                                        addCoordinates();
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            } else if (upRightBeating()) {
                                if (board.getFigure(moves[0] + 1, moves[1] - 1) instanceof Pawn) {
                                    if (!(pawnToMoveColour.equals(board.getFigure(moves[0] + 1, moves[1] - 1).getColour()))) {
                                        addCoordinates();
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            } else if (upLeftBeating()) {
                                if (board.getFigure(moves[0] - 1, moves[1] - 1) instanceof Pawn) {
                                    if (!(pawnToMoveColour.equals(board.getFigure(moves[0] - 1, moves[1] - 1).getColour()))) {
                                        addCoordinates();
                                    } else {
                                        System.out.println("You can't beat your own Pawn");
                                        startMoveMethod = false;
                                    }
                                } else {
                                    System.out.println("Not allowed move. You can move only to addiacent field");
                                    startMoveMethod = false;
                                }
                            } else if (downLeftBeating()) {
                                if (board.getFigure(moves[0] - 1, moves[1] + 1) instanceof Pawn) {
                                    if (!(pawnToMoveColour.equals(board.getFigure(moves[0] - 1, moves[1] + 1).getColour()))) {
                                        addCoordinates();
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
                        } else if (theSame()) {
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
            return new ArrayList<>(board.move(moves[0], moves[1], moves[2], moves[3]));
        }
        return new ArrayList<>();
    }

    private boolean upRight() {
        return (moves[0] + 1) == col & (moves[1] - 1) == row;
    }

    private boolean upLeft() {
        return (moves[0] - 1) == col & (moves[1] - 1) == row;
    }

    private boolean downLeft() {
        return (moves[0] - 1) == col & (moves[1] + 1) == row;
    }

    private boolean downRight() {
        return (moves[0] + 1) == col & (moves[1] + 1) == row;
    }

    private boolean theSame() {
        return moves[0] == col & moves[1] == row;
    }

    private boolean upRightBeating() {
        return (moves[0] + 2) == col & (moves[1] - 2) == row;
    }

    private boolean upLeftBeating() {
        return (moves[0] - 2) == col & (moves[1] - 2) == row;
    }

    private boolean downRightBeating() {
        return (moves[0] + 2) == col & (moves[1] + 2) == row;
    }

    private boolean downLeftBeating() {
        return (moves[0] - 2) == col & (moves[1] + 2) == row;
    }

    private void addCoordinates() {
        moves[2] = col;
        moves[3] = row;
    }
}