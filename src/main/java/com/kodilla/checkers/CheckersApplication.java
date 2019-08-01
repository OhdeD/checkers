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

    private Image board = new Image("file:resources/board.png");
    private Image blackPawn = new Image("file:resources/blackPawn.png");
    private Image whitePawn = new Image("file:resources/whitePawn.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize size = new BackgroundSize(1100, 1100, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(backgroundImage);

        ImagePattern blackPawnPattern= new ImagePattern(blackPawn, 20, 20, 35,35, true);
        ImagePattern whitePawnPattern= new ImagePattern(whitePawn, 20, 20, 35,35, true);


        GridPane grid = new GridPane();

        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                if ((n + m) % 2 == 0 & (m < 2)) {
                    Circle whitePawn = new Circle();
                    whitePawn.setRadius(35);
                    whitePawn.setFill(blackPawnPattern);
                    whitePawn.setStrokeWidth(20);
                    grid.add(whitePawn, 0 + n, 0 + m);
                    grid.setHgap(10);
                    grid.setVgap(10);

                }
                if ((n + m) % 2 == 0 & (m > 5)) {

                    Circle blackPawn = new Circle();
                    blackPawn.setRadius(35);
                    blackPawn.setFill(Color.BLACK);
                    blackPawn.setStrokeWidth(50);
                    grid.add(blackPawn, 0 + n, 0 + m);
                    grid.setHgap(10);
                    grid.setVgap(10);

                }
                if ( ( (n + m)%2==0 & ((m < 6)&(m > 1))  ||  ((n + m) % 2 != 0))  ) {
                    Circle empty = new Circle();
                    empty.setRadius(35);
                    empty.setFill(Color.WHITE);
                    grid.add(empty, 0 + n, 0 + m);
                    grid.setHgap(10);
                    grid.setVgap(10);

                }
            }
        }

        Group root = new Group(grid);

        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}