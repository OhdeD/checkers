package com.kodilla.checkers.logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Welcome {
    private String playersColour;
    private Image boardPart = new Image("file:src/main/resources/windowBackground.png");
    private BackgroundSize size = new BackgroundSize(400, 400, false, false, true, true);
    private BackgroundImage windowBackground = new BackgroundImage(boardPart, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
    private Background background = new Background(windowBackground);
    private Board board;
    private Stage firstWindow;
    private GridPane grid;


    public Welcome(Board board, Stage firstWindow, GridPane grid) {
        this.board = board;
        this.firstWindow = firstWindow;
        this.grid = grid;
    }

    public Scene openWelcomeWindow() {


        Label welcomeText = new Label("WELCOME TO \n CHECKERS!");
        welcomeText.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
        welcomeText.setPadding(new Insets(30, 0, 0, 0));
        welcomeText.setTextFill(Color.WHITE);

        Label chooseColour = new Label("Pick Your colour:");
        chooseColour.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 15));
        chooseColour.setPadding(new Insets(10, 0, 0, 0));
        chooseColour.setTextFill(Color.WHITE);

        Label haveFun = new Label("Have Fun!");
        haveFun.setFont(Font.font(15));
        haveFun.setPadding(new Insets(50, 0, 0, 0));

        Button white = new Button();
        white.setText("WHITE");
        white.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.REGULAR, 15));
        white.setPrefSize(130, 100);
        white.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playersColour = "WHITE";
                board.initBoard(playersColour);
                board.showBoard();
            }
        });

        Button black = new Button();
        black.setText("BLACK");
        black.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.REGULAR, 15));
        black.setPrefSize(130, 100);
        black.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playersColour = "BLACK";
                board.initBoard(playersColour);
                board.showBoard();
            }
        });

        Button play = new Button();
        play.setText("START GAME");
        play.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.REGULAR, 15));
        play.setPrefSize(200, 60);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                firstWindow.close();
            }
        });

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(white, black);
        buttons.setPadding(new Insets(30, 50, 20, 50));
        buttons.setAlignment(Pos.CENTER);

        VBox view = new VBox(0);
        view.getChildren().addAll(welcomeText, chooseColour, buttons, play, haveFun);
        view.setAlignment(Pos.CENTER);

        StackPane window = new StackPane();
        window.getChildren().add(view);
        window.setBackground(background);
        return new Scene(window, 400, 400);
    }
}
