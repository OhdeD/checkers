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
import javafx.stage.Stage;

import java.awt.*;


public class CheckersApplication extends Application {

    private Image board = new Image("file:resources/background.jpg");
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


        GridPane grid = new GridPane();

        for(int n=0; n<8; n++ ){
            for (int m = 0; m<8; m++) {

                if((n+m)%2 ==0 & (m<2) ){
                    ToggleButton button = new  ToggleButton("", new ImageView(blackPawn));
                    button.setVisible(false);
                    grid.add(button, 0 + n, 0 + m);
                } if ((n+m)%2 ==0 & (m>5) ){
                    ToggleButton button = new  ToggleButton("",new ImageView(whitePawn));
                    button.setVisible(false);
                    grid.add(button, 0 + n, 0 + m);
                }
                ToggleButton button = new  ToggleButton();
                button.setVisible(false);
                grid.add(button, 0 + n, 0 + m);

            }
         }

        Group root = new Group(grid);

        Scene scene = new Scene(root, 1000,1000);
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}