package com.kodilla.checkers.logic.moves;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.None;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class OldFigure {
    private int col1;
    private int row1;
    private int col2;
    private int row2;
    private Board board;

    public OldFigure(int col1, int row1, int col2, int row2, Board board) {
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.board = board;
    }

    public List<Node> remove(GridPane grid) {

        List<Node> toRemove = new ArrayList<>();

        boolean ifBeatingUpToTheRight = ((col2 - col1) == 2) & ((row2 - row1) == -2);
        boolean ifBeatingDownToTheRight = ((col2 - col1) == 2) & ((row2 - row1) == 2);
        boolean ifBeatingDownToTheLeft = ((col2 - col1) == -2) & ((row2 - row1) == 2);
        boolean ifBeatingUpToTheLeft = ((col2 - col1) == -2) & ((row2 - row1) == -2);

        for (Node node : grid.getChildren()) {
            if (node instanceof Circle) {
                if ((col1 + "-" + row1).equals(node.getId())) {
                    toRemove.add(node);
                }

                if (ifBeatingDownToTheRight) {
                    if (((col1 + 1) + "-" + (row1 + 1)).equals(node.getId())) {
                        toRemove.add(node);
                        board.setFigure(col1 + 1, row1 + 1, new None());
                    }
                } else if (ifBeatingUpToTheRight) {
                    if (((col1 + 1) + "-" + (row1 - 1)).equals(node.getId())) {
                        toRemove.add(node);
                        board.setFigure(col1 + 1, row1 - 1, new None());
                    }
                } else if (ifBeatingUpToTheLeft) {
                    if (((col1 - 1) + "-" + (row1 - 1)).equals(node.getId())) {
                        toRemove.add(node);
                        board.setFigure(col1 - 1, row1 - 1, new None());
                    }
                } else if (ifBeatingDownToTheLeft) {
                    if (((col1 - 1) + "-" + (row1 + 1)).equals(node.getId())) {
                        toRemove.add(node);
                        board.setFigure(col1 - 1, row1 + 1, new None());
                    }
                }
            }
        }
        return toRemove;
    }
}
