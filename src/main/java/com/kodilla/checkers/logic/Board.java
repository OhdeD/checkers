package com.kodilla.checkers.logic;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private static String PLAYERS_COLOUR;
    private static String COMP_COLOUR ;
    private static final int BOARD_WIDTH = 835;

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

    public void setColours() {
        PLAYERS_COLOUR = Welcome.getPlayersColour();
        if (PLAYERS_COLOUR.equals("WHITE")){
            COMP_COLOUR = "BLACK";
        }else {COMP_COLOUR = "WHITE";}
    }

    public void initBoard() {
        setColours();

        setFigure(0, 0, new Pawn(COMP_COLOUR));
        setFigure(2, 0, new Pawn(COMP_COLOUR));
        setFigure(4, 0, new Pawn(COMP_COLOUR));
        setFigure(6, 0, new Pawn(COMP_COLOUR));
        setFigure(1, 1, new Pawn(COMP_COLOUR));
        setFigure(3, 1, new Pawn(COMP_COLOUR));
        setFigure(5, 1, new Pawn(COMP_COLOUR));
        setFigure(7, 1, new Pawn(COMP_COLOUR));
        setFigure(0, 2, new Pawn(COMP_COLOUR));
        setFigure(2, 2, new Pawn(COMP_COLOUR));
        setFigure(4, 2, new Pawn(COMP_COLOUR));
        setFigure(6, 2, new Pawn(COMP_COLOUR));

        setFigure(1, 5, new Pawn(PLAYERS_COLOUR));
        setFigure(3, 5, new Pawn(PLAYERS_COLOUR));
        setFigure(5, 5, new Pawn(PLAYERS_COLOUR));
        setFigure(7, 5, new Pawn(PLAYERS_COLOUR));
        setFigure(0, 6, new Pawn(PLAYERS_COLOUR));
        setFigure(2, 6, new Pawn(PLAYERS_COLOUR));
        setFigure(4, 6, new Pawn(PLAYERS_COLOUR));
        setFigure(6, 6, new Pawn(PLAYERS_COLOUR));
        setFigure(1, 7, new Pawn(PLAYERS_COLOUR));
        setFigure(3, 7, new Pawn(PLAYERS_COLOUR));
        setFigure(5, 7, new Pawn(PLAYERS_COLOUR));
        setFigure(7, 7, new Pawn(PLAYERS_COLOUR));
    }

    public List<Node> move(int col1, int row1, int col2, int row2) {
        Figure f = getFigure(col1, row1);
        ImagePattern p = (f.getColour().equals(COMP_COLOUR)) ? blackPawnPattern : whitePawnPattern;
        Circle systemPawn = new Circle();
        systemPawn.setRadius(50);
        systemPawn.setFill(p);
        systemPawn.setStrokeWidth(50);
        systemPawn.setId(col2 + "-" + row2);
        grid.add(systemPawn, col2, row2);

        List<Node> toRemove = new ArrayList<>();

        boolean ifUpToTheRight = ((col2 - col1) == 2) & ((row2 - row1) == -2);
        boolean ifDownToTheRight = ((col2 - col1) == 2) & ((row2 - row1) == 2);
        boolean ifDownToTheLeft = ((col2 - col1) == -2) & ((row2 - row1) == 2);
        boolean ifUpToTheLeft = ((col2 - col1) == -2) & ((row2 - row1) == -2);

        for (Node node : grid.getChildren()) {
            if (node instanceof Circle) {
                if ((col1 + "-" + row1).equals(node.getId())) {
                    toRemove.add(node);
                    System.out.println("obraz piona który mial byc poruszony - dodany do usuniecia");
                }
                if (ifDownToTheRight) {
                    if (((col1 + 1) + "-" + (row1 + 1)).equals(node.getId())) {
                        toRemove.add(node);
                        System.out.println("Ruch bicia. Obraz piona przeciwnika dodany do listy do usuniecia");
                    }
                } else if (ifUpToTheRight) {
                    if (((col1 + 1) + "-" + (row1 - 1)).equals(node.getId())) {
                        toRemove.add(node);
                        System.out.println("Ruch bicia. Obraz piona przeciwnika dodany do listy do usuniecia");
                    }
                } else if (ifUpToTheLeft) {
                    if (((col1 - 1) + "-" + (row1 - 1)).equals(node.getId())) {
                        toRemove.add(node);
                        System.out.println("Ruch bicia. Obraz piona przeciwnika dodany do listy do usuniecia");
                    }
                } else if (ifDownToTheLeft) {
                    if (((col1 - 1) + "-" + (row1 + 1)).equals(node.getId())) {
                        toRemove.add(node);
                        System.out.println("Ruch bicia. Obraz piona przeciwnika dodany do listy do usuniecia");
                    }
                }
            }
        }
        setFigure(col1, row1, new None());
        setFigure(col2, row2, f);
        if (ifDownToTheRight) {
            setFigure(col1 + 1, row1 + 1, new None());
        } else if (ifUpToTheRight) {
            setFigure(col1 + 1, row1 - 1, new None());
        } else if (ifUpToTheLeft) {
            setFigure(col1 - 1, row1 - 1, new None());
        } else if (ifDownToTheLeft) {
            setFigure(col1 - 1, row1 + 1, new None());
        }
        return toRemove;
    }

    public void showBoard() {
        grid.getChildren().clear();
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if (getFigure(col, row) instanceof Pawn) {
                    ImagePattern p = (getFigure(col, row).getColour().equals(COMP_COLOUR)) ? blackPawnPattern : whitePawnPattern;
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
        String colourOfPickedPawn = getFigure(col, row).getColour();

        if (getFigure(col, row) instanceof Pawn) {
            if (col == 7 & row == 7) {
                moveUpToTheLeft(col, row, moveOption1, colourOfPickedPawn);
                beatingUpToTheLeft(col, row, moveOption1, colourOfPickedPawn);
            }
            if (col == 0 & row == 0) {
                //  moveDownToTheRight(col, row, moveOption1, colourOfPickedPawn);
                beatingDownToTheRight(col, row, moveOption1, colourOfPickedPawn);
            }
            // if (col > 0 & col < 7 & row == 0) {
            //    moveDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
            //    moveDownToTheRight(col, row, moveOption2, colourOfPickedPawn);
            //}
            if (col > 1 & col < 6 & row == 0) {
                beatingDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
                beatingDownToTheRight(col, row, moveOption2, colourOfPickedPawn);
            }
            if (col > 0 & col < 7 & row == 7) {
                moveUpToTheLeft(col, row, moveOption1, colourOfPickedPawn);
                moveUpToTheRight(col, row, moveOption2, colourOfPickedPawn);
            }
            if (col > 1 & col < 6 & row == 7) {
                beatingUpToTheLeft(col, row, moveOption1, colourOfPickedPawn);
                beatingUpToTheRight(col, row, moveOption2, colourOfPickedPawn);
            }
            if (row > 0 & row < 7 & col == 0) {
                moveUpToTheRight(col, row, moveOption2, colourOfPickedPawn);
                //  moveDownToTheRight(col, row, moveOption1, colourOfPickedPawn);
            }

            if (row > 1 & row < 6 & col == 0) {
                beatingUpToTheRight(col, row, moveOption2, colourOfPickedPawn);
                beatingDownToTheRight(col, row, moveOption1, colourOfPickedPawn);
            }
            if (row > 0 & row < 7 & col == 7) {
                moveUpToTheLeft(col, row, moveOption2, colourOfPickedPawn);
                // moveDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
            }
            if (row > 1 & row < 6 & col == 7) {
                beatingUpToTheLeft(col, row, moveOption2, colourOfPickedPawn);
                beatingDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
            }
        }
        if (row > 0 & row < 7 & col > 0 & col < 7) {
            moveUpToTheLeft(col, row, moveOption2, colourOfPickedPawn);
            //  moveDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
            moveUpToTheRight(col, row, moveOption4, colourOfPickedPawn);
            //  moveDownToTheRight(col, row, moveOption3, colourOfPickedPawn);
        }
        if (row > 1 & row < 6 & col > 1 & col < 6) {
            beatingUpToTheLeft(col, row, moveOption2, colourOfPickedPawn);
            beatingDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
            beatingUpToTheRight(col, row, moveOption4, colourOfPickedPawn);
            beatingDownToTheRight(col, row, moveOption3, colourOfPickedPawn);
        }
    }


    private void moveUpToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if (!(getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            grid.add(moveOption2, col + 1, row - 1);
        }
    }

    private void beatingUpToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if ((getFigure((col + 1), (row - 1)) instanceof Pawn)) {
            if ((!getFigure((col + 1), (row - 1)).getColour().equals(colourOfPickedPawn))) {
                if (!(getFigure((col + 2), (row - 2)) instanceof Pawn)) {
                    grid.add(moveOption2, col + 2, row - 2);
                }
            }
        }
    }

    private void moveUpToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if (!(getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            grid.add(moveOption1, col - 1, row - 1);
        }
    }

    private void beatingUpToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if ((getFigure((col - 1), (row - 1)) instanceof Pawn)) {
            if (!getFigure((col - 1), (row - 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(getFigure((col - 2), (row - 2)) instanceof Pawn)) {
                    grid.add(moveOption1, col - 2, row - 2);
                }
            }
        }
    }

    private void moveDownToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if (!(getFigure((col + 1), (row + 1)) instanceof Pawn)) {
            grid.add(moveOption2, col + 1, row + 1);
        }
    }

    private void beatingDownToTheRight(int col, int row, Circle moveOption2, String colourOfPickedPawn) {
        if ((getFigure((col + 1), (row + 1)) instanceof Pawn)) {
            if (!getFigure((col + 1), (row + 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(getFigure((col + 2), (row + 2)) instanceof Pawn)) {
                    grid.add(moveOption2, col + 2, row + 2);
                }
            }
        }
    }

    private void middleOnTheRight(int col, int row, Circle moveOption1, Circle moveOption2) {
        String colourOfPickedPawn = getFigure(col, row).getColour();

        moveDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);
        beatingDownToTheLeft(col, row, moveOption1, colourOfPickedPawn);

        moveUpToTheLeft(col, row, moveOption2, colourOfPickedPawn);
        beatingUpToTheLeft(col, row, moveOption2, colourOfPickedPawn);
    }

    private void moveDownToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if (!(getFigure((col - 1), (row + 1)) instanceof Pawn)) {
            grid.add(moveOption1, col - 1, row + 1);
        }
    }

    private void beatingDownToTheLeft(int col, int row, Circle moveOption1, String colourOfPickedPawn) {
        if ((getFigure((col - 1), (row + 1)) instanceof Pawn)) {
            if (!getFigure((col - 1), (row + 1)).getColour().equals(colourOfPickedPawn)) {
                if (!(getFigure((col - 2), (row + 2)) instanceof Pawn)) {
                    grid.add(moveOption1, col - 2, row + 2);
                }
            }
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

    int[] moves = new int[8];

    List<Node> picked = new ArrayList<>();

    public boolean startMove(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;
        boolean firstMove = true;

        for (int x = 35; x < 835; x += 100) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = 35; y < 835; y += 100) {
                    if ((y <= mouseEvent.getY()) & (mouseEvent.getY() <= (y + 100))) {
                        // highligting picked pawn
                        if (getFigure(col, row) instanceof Pawn) {
                            ImagePattern p = (getFigure(col, row).getColour().equals(COMP_COLOUR)) ? blackPawnPattern : whitePawnPattern;
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

    public List<Node> endMove(MouseEvent mouseEvent) {

        int col = 0;
        int row = 0;
        boolean startMoveMethod = true;

        grid.getChildren().removeAll(picked);

        for (int x = 35; x < BOARD_WIDTH; x += 100) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = 35; y < 835; y += 100) {
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
                            System.out.println("Pawn is not picked anymore");
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
        List<Coordinates> computersPawns = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (getFigure(col, row) instanceof Pawn) {
                    if (getFigure(col, row).getColour().equals(COMP_COLOUR)) {
                        computersPawns.add(new Coordinates(col, row, 0, 0));
                    }
                }
            }
        }
        Set<Coordinates> moveDownToTheRight = computersPawns.stream()
                .filter(e -> e.getCol() < 7 && e.getRow() < 7)
                .filter(e -> getFigure((e.getCol() + 1), (e.getRow() + 1)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() + 1, e.getRow() + 1))
                .collect(Collectors.toSet());
        Set<Coordinates> moveDownToTheLeft = computersPawns.stream()
                .filter(e -> e.getCol() > 0 && e.getRow() < 7)
                .filter(e -> getFigure((e.getCol() - 1), (e.getRow() + 1)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() - 1, e.getRow() + 1))
                .collect(Collectors.toSet());


        Set<Coordinates> beatingDownToTheRight = computersPawns.stream()
                .filter(e -> e.getCol() < 6 && e.getRow() < 6)
                .filter(e -> getFigure((e.getCol() + 1), (e.getRow() + 1)) instanceof Pawn)
                .filter(e -> !getFigure((e.getCol() + 1), (e.getRow() + 1)).getColour().equals(getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> getFigure((e.getCol() + 2), (e.getRow() + 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() + 2, e.getRow() + 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingDownToTheLeft = computersPawns.stream()
                .filter(e -> e.getCol() > 1 && e.getRow() < 6)
                .filter(e -> getFigure((e.getCol() - 1), (e.getRow() + 1)) instanceof Pawn)
                .filter(e -> !getFigure((e.getCol() - 1), (e.getRow() + 1)).getColour().equals(getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> getFigure((e.getCol() - 2), (e.getRow() + 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getCol() - 2, e.getRow() + 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingUpToTheRight = computersPawns.stream()
                .filter(e -> e.getCol() < 6 && e.getRow() > 1)
                .filter(e -> getFigure((e.getCol() + 1), (e.getRow() - 1)) instanceof Pawn)
                .filter(e -> !getFigure((e.getCol() + 1), (e.getRow() - 1)).getColour().equals(getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> getFigure((e.getCol() + 2), (e.getRow() - 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getColToMove() + 2, e.getRowToMove() - 2))
                .collect(Collectors.toSet());
        Set<Coordinates> beatingUpToTheLeft = computersPawns.stream()
                .filter(e -> e.getCol() > 1 && e.getRow() > 1)
                .filter(e -> getFigure((e.getCol() - 1), (e.getRow() - 1)) instanceof Pawn)
                .filter(e -> !getFigure((e.getCol() - 1), (e.getRow() - 1)).getColour().equals(getFigure(e.getCol(), e.getRow()).getColour()))
                .filter(e -> getFigure((e.getCol() - 2), (e.getRow() - 2)) instanceof None)
                .map(e -> new Coordinates(e.getCol(), e.getRow(), e.getColToMove() - 2, e.getRowToMove() - 2))
                .collect(Collectors.toSet());

        Set<Set<Coordinates>> moves = new HashSet<>();
        moves.add(moveDownToTheRight);
        moves.add(moveDownToTheLeft);


        Set<Set<Coordinates>> beatings = new HashSet<>();
        beatings.add(beatingUpToTheRight);
        beatings.add(beatingUpToTheLeft);
        beatings.add(beatingDownToTheRight);
        beatings.add(beatingDownToTheLeft);

        Optional<Coordinates> beatingCoordinate = beatings.stream()
                .flatMap(Collection::stream)
                .findAny();
        Optional<Coordinates> moveCoordinate = moves.stream()
                .flatMap(Collection::stream)
                .findAny();

        Coordinates coordinates = beatingCoordinate.orElse(moveCoordinate.get());
        int col1 = coordinates.getCol();
        int row1 = coordinates.getRow();
        int col2 = coordinates.getColToMove();
        int row2 = coordinates.getRowToMove();

        computersPawns.clear();
        return new ArrayList<>(move(col1, row1, col2, row2));
    }
}


