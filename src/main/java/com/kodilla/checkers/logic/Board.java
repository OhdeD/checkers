package com.kodilla.checkers.logic;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

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

    public void move(int col1, int row1, int col2, int row2) {
        Figure f = getFigure(col1, row1);
        setFigure(col2, row2, f);
        setFigure(col1, row1, new None());
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

    public void click(MouseEvent mouseEvent) {

        char y = 'a';
        int yy = 8;

        for (int b = 35; b < 900; b += 100) {
            if ((b <= mouseEvent.getX()) & (mouseEvent.getX() <= (b+100))) {

                for (int a = 35; a < 900; a += 100) {
                    if ((a <= mouseEvent.getY()) & (mouseEvent.getY() <= (a + 100))) {
                        System.out.println("" + y + yy);
                    }
                    yy--;
                }
            }
            y+=1;
        }
    }
}
