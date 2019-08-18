package com.kodilla.checkers;

import com.kodilla.checkers.logic.Board;
import com.kodilla.checkers.logic.EndWindow;
import com.kodilla.checkers.logic.Welcome;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class CheckersApplication extends Application {

    private Image board = new Image("file:src/main/resources/board.png");
    private Image blackPawn = new Image("file:src/main/resources/pionekczarny.png");
    private Image whitePawn = new Image("file:src/main/resources/PIONEKbialy.png");
    private String playersColour = "WHITE";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize size = new BackgroundSize(900, 900, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(backgroundImage);

        ImagePattern blackPawnPattern = new ImagePattern(blackPawn, 0, 0, 1, 1, true);
        ImagePattern whitePawnPattern = new ImagePattern(whitePawn, 0, 0, 1, 1, true);



        GridPane grid = new GridPane();
        grid.setPadding(new Insets(35, 35, 35, 35));
        grid.setBackground(background);

        for (int i = 0; i < 8; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(100));
        }
        for (int i = 0; i < 8; i++) {
            grid.getRowConstraints().add(new RowConstraints(100));
        }

        Board board = new Board(grid, blackPawnPattern, whitePawnPattern);
        board.initBoard(playersColour);
        board.showBoard();

        Group root = new Group(grid);
        Scene scene = new Scene(root, 870, 870);


        scene.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                board.enter(mouseEvent);
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List<Node> toRemove = board.exit(mouseEvent);
                grid.getChildren().removeAll(toRemove);
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            boolean firstMove = true;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (firstMove) {
                    boolean executed = board.startMove(mouseEvent);
                    if (executed) {
                        firstMove = false;
                    }
                } else {
                    List<Node> nodesToRemove = new ArrayList<>(board.endMove(mouseEvent));
                    if (nodesToRemove.size() == 0) {
                        firstMove = true;
                    } else {
                        grid.getChildren().removeAll(nodesToRemove);

                        List<Node> computerNodesToRemove = board.computersMove();
                        grid.getChildren().removeAll(computerNodesToRemove);
                        System.out.println("It's computer's move now.");
                        firstMove = true;

                        if (board.isEndGame()) {
                            Stage endWindow = new Stage();
                            endWindow.setTitle("End of the Game");
                            endWindow.initModality(Modality.WINDOW_MODAL);
                            endWindow.initOwner(primaryStage);
                            endWindow.setScene(new EndWindow(board.getWinner()).openEndWindow());
                            endWindow.show();

                        }
                    }
                }
            }
        });

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage firstWindow = new Stage();
        firstWindow.setTitle("Welcome to OhdeD's CHECKERS ;)");
        firstWindow.initModality(Modality.WINDOW_MODAL);
        firstWindow.initOwner(primaryStage);
        firstWindow.setScene(new Welcome().openWelcomeWindow());
        firstWindow.show();
    }
}
