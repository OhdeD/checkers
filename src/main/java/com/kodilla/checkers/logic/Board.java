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
    private static final int BOARD_WIDTH = 835;
    private static final int BOARD_HIGHT = 835;
    private static final int FIELD_WIDTH = 100;
    private static final int FIELD_HIGHT = 100;
    private static final int PADDING = 35;
    private String PLAYERS_COLOUR;
    private String COMP_COLOUR;
    private List<BoardRow> rows = new ArrayList<>();

    private GridPane grid;

    private ImagePattern blackPawnPattern;
    private ImagePattern whitePawnPattern;
    private ImagePattern PLAYERS_PATTERN;
    private ImagePattern COMP_PATTERN;

    private boolean endGame;

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


    public void setColours() {
        PLAYERS_COLOUR = Welcome.getPlayersColour();
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
        NewFigure newFigure = new NewFigure(col1,row1,col2,row2,this);
        grid.add(newFigure.newFigureToGrid(), col2, row2);
        setFigure(col2, row2, newFigure.newFigure());

        setFigure(col1, row1, new None());
        OldFigure oldFigure = new OldFigure(col1,row1,col2,row2,this);

        if (row2 == 0 || row2 == 7) {
            endGame = true;
        }
        return oldFigure.remove(grid);
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
    }

    public void showMoveOption(int col, int row) {
       PossibleMovesDisplay possibleMovesDisplay = new PossibleMovesDisplay(col,row,this);
        addToGrid(possibleMovesDisplay.display());
    }

    public void addToGrid(List<ToAddToGrid> toAddToGrid) {
        for (ToAddToGrid i: toAddToGrid) {
            if (i != null) {
                grid.add(i.getMoveOption(), i.getCol(), i.getRow());
            }
        }
    }

    public void enter(MouseEvent mouseEvent) {
        int col = 0;
        int row = 0;

        for (int x = PADDING; x < BOARD_WIDTH; x += FIELD_WIDTH) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = PADDING; y < BOARD_HIGHT; y += FIELD_HIGHT) {
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

        for (int x = PADDING; x < BOARD_WIDTH; x += FIELD_WIDTH) {
            if ((x <= mouseEvent.getX()) & (mouseEvent.getX() <= (x + 100))) {
                for (int y = PADDING; y < BOARD_HIGHT; y += FIELD_HIGHT) {
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

    public boolean isEndGame() {
        return endGame;
    }

    public  String getPlayersColour() {
        return PLAYERS_COLOUR;
    }

    public ImagePattern getPLAYERS_PATTERN() {
        return PLAYERS_PATTERN;
    }

    public ImagePattern getCOMP_PATTERN() {
        return COMP_PATTERN;
    }
}


