package com.kodilla.checkers;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;


public class CheckersApplication extends Application {

    private Image board = new Image("file:src/main/resources/board.png");
    private Image blackPawn = new Image("file:src/main/resources/pionekczarny.png");
    private Image whitePawn = new Image("file:src/main/resources/PIONEKbialy.png");

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
        for (int i = 0; i < 8; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(100));
        }
        for (int i = 0; i < 8; i++) {
            grid.getRowConstraints().add(new RowConstraints(100));
        }

        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                if ((n + m) % 2 == 0 & (m < 2)) {
                    Circle whitePawn = new Circle();
                    whitePawn.setRadius(50);
                    whitePawn.setFill(whitePawnPattern);
                    whitePawn.setStrokeWidth(50);
                    grid.add(whitePawn, 0 + n, 0 + m);


                }
                if ((n + m) % 2 == 0 & (m > 5)) {

                    Circle blackPawn = new Circle();
                    blackPawn.setRadius(50);
                    blackPawn.setFill(blackPawnPattern);
                    blackPawn.setStrokeWidth(50);
                    grid.add(blackPawn, 0 + n, 0 + m);
                    grid.setHgap(0);
                    grid.setVgap(0);

                }
            }
        }

        grid.setPadding(new Insets(35,35,35,35));
        grid.setBackground(background);
        Group root = new Group(grid);

        Scene scene = new Scene(root, 870, 870);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}