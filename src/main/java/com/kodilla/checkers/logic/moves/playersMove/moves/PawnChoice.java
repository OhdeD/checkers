package com.kodilla.checkers.logic.moves.playersMove.moves;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Pawn;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.List;

import static com.kodilla.checkers.logic.Board.*;

public class PawnChoice {
    private Board board;
    private int BOARD_WIDTH;
    private int BOARD_HIGHT;
    private int FIELD_WIDTH;
    private int FIELD_HIGHT;
    private int PADDING;
    private GridPane grid;
    private int[] moves = new int[8];
    private List<Node> picked;

    public PawnChoice(Board board, GridPane grid) {
        this.board = board;
        this.grid = grid;

        BOARD_HIGHT = getBoardHight();
        BOARD_WIDTH = getBoardWidth();
        FIELD_HIGHT = getFieldHight();
        FIELD_WIDTH = getFieldWidth();
        PADDING = getPADDING();
    }

    public boolean pawnChoice(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;
        boolean firstMove = true;

        for (int x = PADDING; x < BOARD_WIDTH; x += FIELD_WIDTH) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = PADDING; y < BOARD_HIGHT; y += FIELD_HIGHT) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
                        if (board.getFigure(col, row) instanceof Pawn) {
                            MarkingPickedPawn m = new MarkingPickedPawn(board, grid);
                            m.marking(col, row);
                            picked = m.getPicked();
                            moves[0] = col;
                            moves[1] = row;
                            System.out.println("col & row added to table ");
                            return firstMove;
                        } else {
                            System.out.println("You have to pick some Pawn");
                            firstMove = false;
                        }
                    }
                    row++;
                }
            }
            col += 1;
        }
        return firstMove;
    }

    public int[] getMoves() {
        return moves;
    }

    public List<Node> getPicked() {
        return picked;
    }
}