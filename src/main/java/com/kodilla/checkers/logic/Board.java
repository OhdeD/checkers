package com.kodilla.checkers.logic;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class Board {
    private static final String WHITE = "WHITE";
    private static final String BLACK = "BLACK";

    private List<BoardRow> rows = new ArrayList<>();
    private GridPane grid;
    private ImagePattern blackPawnPattern;
    private ImagePattern whitePawnPattern;

    public Board(GridPane grid, ImagePattern blackPawnPattern, ImagePattern whitePawnPattern) {
        this.grid = grid;
        this.blackPawnPattern = blackPawnPattern;
        this.whitePawnPattern = whitePawnPattern;

        for (int n = 0; n < 8; n++) {
            rows.add(new BoardRow());
        }
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().add(col, figure);
        rows.get(row).getCols().remove(col + 1);
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void initBoard() {
        setFigure(0, 0, new Pawn(WHITE));
        setFigure(2, 0, new Pawn(WHITE));
        setFigure(4, 0, new Pawn(WHITE));
        setFigure(6, 0, new Pawn(WHITE));
        setFigure(1, 1, new Pawn(WHITE));
        setFigure(3, 1, new Pawn(WHITE));
        setFigure(5, 1, new Pawn(WHITE));
        setFigure(7, 1, new Pawn(WHITE));
        setFigure(0, 6, new Pawn(BLACK));
        setFigure(2, 6, new Pawn(BLACK));
        setFigure(4, 6, new Pawn(BLACK));
        setFigure(6, 6, new Pawn(BLACK));
        setFigure(1, 7, new Pawn(BLACK));
        setFigure(3, 7, new Pawn(BLACK));
        setFigure(5, 7, new Pawn(BLACK));
        setFigure(7, 7, new Pawn(BLACK));
    }

    public Node move(int col1, int row1, int col2, int row2) {
        Figure f = getFigure(col1, row1);
        ImagePattern p = (f.getColour().equals(BLACK)) ? blackPawnPattern : whitePawnPattern;
        Circle systemPawn = new Circle();
        systemPawn.setRadius(50);
        systemPawn.setFill(p);
        systemPawn.setStrokeWidth(50);
        systemPawn.setId(col2 + "-" + row2);
        grid.add(systemPawn, col2, row2);

        Node toRemove = null;
        for (Node node : grid.getChildren()) {
            if (node instanceof Circle) {

                if ((col1 + "-" + row1).equals(node.getId())) {
                    toRemove = node;
                }
            }
        }

        setFigure(col1, row1, new None());
        setFigure(col2, row2, f);
        return toRemove;
    }

    public void showBoard() {
        grid.getChildren().clear();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (getFigure(col, row) instanceof Pawn) {
                    ImagePattern p = (getFigure(col, row).getColour().equals(BLACK)) ? blackPawnPattern : whitePawnPattern;
                    Circle systemPawn = new Circle();
                    systemPawn.setRadius(50);
                    systemPawn.setFill(p);
                    systemPawn.setStrokeWidth(50);
                    systemPawn.setId(col + "-" + row);
                    grid.add(systemPawn, col, row);
                }
            }
        }
//        for (int n = 0; n < 8; n++) {
//            for (int m = 0; m < 8; m++) {
//                if ((n + m) % 2 == 0 & (m < 2)) {
//                    Circle systemPawn = new Circle();
//                    systemPawn.setRadius(50);
//                    systemPawn.setFill(whitePawnPattern);
//                    systemPawn.setStrokeWidth(50);
//                    grid.add(systemPawn, 0 + n, 0 + m);
//                }
//                if ((n + m) % 2 == 0 & (m > 5)) {
//                    Circle playersPawn = new Circle();
//                    playersPawn.setRadius(50);
//                    playersPawn.setFill(blackPawnPattern);
//                    playersPawn.setStrokeWidth(50);
//                    grid.add(playersPawn, 0 + n, 0 + m);
//                    grid.setHgap(0);
//                    grid.setVgap(0);
//                }
//            }
//        }
    }

    public void showMoveOption(int col, int row) {
        List<Circle> highlight = new ArrayList<>();
        Circle moveOption1 = new Circle();
        Circle moveOption2 = new Circle();
        Circle moveOption3 = new Circle();
        Circle moveOption4 = new Circle();
        highlight.add(moveOption1);
        highlight.add(moveOption2);
        highlight.add(moveOption3);
        highlight.add(moveOption4);

        for (Circle i : highlight) {
            i.setId("highlight");
            i.setRadius(50);
            i.setStrokeWidth(50);
            i.setFill(Color.rgb(255, 255, 128, 0.2));
        }

        if (getFigure(col, row) instanceof Pawn) {
            if (col == 7 & row == 7) {
                if (!(getFigure((col - 1), (row - 1)) instanceof Pawn)) {
                    grid.add(moveOption1, col - 1, row - 1);
                }
            }
            if (col == 0 & row == 0) {
                if (!(getFigure((col + 1), (row + 1)) instanceof Pawn)) {
                    grid.add(moveOption1, col + 1, row + 1);
                }
            }
            if (col > 0 & col < 7 & row == 0) {
                if (!(getFigure((col - 1), (row + 1)) instanceof Pawn)) {
                    grid.add(moveOption1, col - 1, row + 1);
                }
                if (!(getFigure((col + 1), (row + 1)) instanceof Pawn)) {
                    grid.add(moveOption2, col + 1, row + 1);
                }
            }
            if (col > 0 & col < 7 & row == 7) {
                if (!(getFigure((col - 1), (row - 1)) instanceof Pawn)) {
                    grid.add(moveOption1, col - 1, row - 1);
                }
                if (!(getFigure((col + 1), (row - 1)) instanceof Pawn)) {
                    grid.add(moveOption2, col + 1, row - 1);
                }
            }
            if (row > 0 & row < 7 & col == 0) {
                middleOnTheLeft(col, row, moveOption1, moveOption2);
            }
            if (row > 0 & row < 7 & col == 7) {
                middleOnTheRight(col, row, moveOption1, moveOption2);
            }
            if (row > 0 & row < 7 & col > 0 & col < 7) {
                middleOnTheRight(col, row, moveOption1, moveOption2);
                middleOnTheLeft(col, row, moveOption3, moveOption4);
            }
        }
    }

    private void middleOnTheRight(int col, int row, Circle moveOption1, Circle moveOption2) {
        if (!(getFigure((col - 1), (row + 1)) instanceof Pawn)) {
            grid.add(moveOption1, col - 1, row + 1);
        }
        if (!(getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            grid.add(moveOption2, col - 1, row - 1);
        }
    }

    private void middleOnTheLeft(int col, int row, Circle moveOption1, Circle moveOption2) {
        if (!(getFigure((col + 1), (row + 1)) instanceof Pawn)) {
            grid.add(moveOption1, col + 1, row + 1);
        }
        if (!(getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            grid.add(moveOption2, col + 1, row - 1);
        }
    }

    public void enter(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;

        for (int x = 35; x < 835; x += 100) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = 35; y < 835; y += 100) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
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

    int[] moves = new int[4];

    List<Node>picked = new ArrayList<>();

    public void startMove(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;

        for (int x = 35; x < 835; x += 100) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = 35; y < 835; y += 100) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
                        if (getFigure(col, row) instanceof Pawn) {
                            ImagePattern p = (getFigure(col, row).getColour().equals(BLACK)) ? blackPawnPattern : whitePawnPattern;
                            Circle c = new Circle();
                            c.setRadius(50);
                            c.setFill(Color.rgb(102, 153, 255, 0.2));
                            c.setStrokeWidth(50);
                            c.setId("c");
                            grid.add(c, col, row);

                            for (Node node : grid.getChildren()) {
                                if (node instanceof Circle) {
                                    if ("c".equals(node.getId())) {
                                        picked.add(node);
                                    }
                                }
                            }

                            moves[0] = col;
                            moves[1] = row;
                            System.out.println("col & row added to list ");
                        } else {
                            System.out.println("empty");
                        }

                    }
                    row++;
                }
            }
            col += 1;
        }
    }

    public Node endMove(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;
        grid.getChildren().removeAll(picked);

        for (int x = 35; x < 835; x += 100) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = 35; y < 835; y += 100) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
                        if (!(getFigure(col, row) instanceof Pawn)) {
                            if (((moves[0] + 1) == col & (moves[1] + 1) == row) ){
                                moves[2] = col;
                                moves[3] = row;
                                System.out.println("col & row added on 2 & 3");
                            }
                            if (((moves[0] + 1) == col & (moves[1] - 1) == row)) {
                                moves[2] = col;
                                moves[3] = row;
                                System.out.println("col & row added on 2 & 3");
                            } if (((moves[0] - 1) == col & (moves[1] - 1) == row) ){
                                moves[2] = col;
                                moves[3] = row;
                                System.out.println("col & row added on 2 & 3");
                            } if ((moves[0] - 1) == col & (moves[1] + 1) == row){
                                moves[2] = col;
                                moves[3] = row;
                                System.out.println("col & row added on 2 & 3");
                            }

                        }
                        else {
                            System.out.println("not empty field");
                        }

                    }
                    row++;
                }
            }
            col += 1;
        }
        return move(moves[0], moves[1], moves[2], moves[3]);
    }
}
