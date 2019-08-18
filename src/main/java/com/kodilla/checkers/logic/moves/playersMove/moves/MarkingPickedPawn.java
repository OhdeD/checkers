package com.kodilla.checkers.logic.moves.playersMove.moves;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.Pawn;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class MarkingPickedPawn {
    private Board board;
    private GridPane grid;
    private List<Node> picked = new ArrayList<>();

    public MarkingPickedPawn(Board board, GridPane grid) {
        this.board = board;
        this.grid = grid;
    }

    public List<Node> getPicked() {
        return picked;
    }

    public void marking(int col, int row) {
        if (board.getFigure(col, row) instanceof Pawn) {
            Circle c = new Circle();
            c.setRadius(50);
            c.setFill(Color.rgb(102, 153, 255, 0.2));
            c.setStrokeWidth(50);
            c.setId("blue");
            grid.add(c, col, row);

            for (Node node : grid.getChildren()) {
                if (node instanceof Circle) {
                    if ("blue".equals(node.getId())) {
                        picked.add(node);
                    }
                }
            }
        }
    }
}
