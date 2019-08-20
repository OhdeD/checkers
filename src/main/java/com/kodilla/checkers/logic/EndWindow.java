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

import static com.kodilla.checkers.CheckersApplication.firstScene;

public class EndWindow {
    private Image boardPart = new Image("file:src/main/resources/windowBackground.png");
    private BackgroundSize size = new BackgroundSize(400, 400, false, false, true, true);
    private BackgroundImage windowBackground = new BackgroundImage(boardPart, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
    private Background background = new Background(windowBackground);
    private String winner;
    private Stage endWindow;
    private Stage primaryStage;
    private boolean newFirstWindow = false;
    private Board board;
    private GridPane grid;

    public EndWindow(String winner, Stage endWindow, Stage primaryStage, Board board, GridPane grid) {
        this.winner = winner;
        this.endWindow = endWindow;
        this.primaryStage = primaryStage;
        this.board = board;
        this.grid = grid;
    }

    public Scene openEndWindow() {

        Label endText = new Label("End of the Game");
        endText.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
        endText.setPadding(new Insets(30, 0, 0, 0));
        endText.setTextFill(Color.WHITE);

        Label winnerIs = new Label("The winner is:");
        winnerIs.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 15));
        winnerIs.setPadding(new Insets(10, 0, 0, 0));
        winnerIs.setTextFill(Color.WHITE);

        Label whoWon = new Label("" + winner);
        whoWon.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 20));
        whoWon.setPadding(new Insets(10, 0, 0, 0));
        whoWon.setTextFill(Color.WHITE);
        whoWon.setPadding(new Insets(0, 0, 50, 0));

        Button playAgain = new Button();
        playAgain.setText("Play again?");
        playAgain.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.REGULAR, 15));
        playAgain.setPrefSize(200, 60);
        playAgain.setPadding(new Insets(0, 0, 10, 0));
        playAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                endWindow.close();
                firstScene(board, primaryStage, grid );
            }
        });

        Button closeWindow = new Button();
        closeWindow.setText("Close");
        closeWindow.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.REGULAR, 10));
        closeWindow.setPrefSize(200, 40);
        closeWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                endWindow.close();
                primaryStage.close();
            }
        });
        VBox view = new VBox(0);
        view.getChildren().addAll(endText, winnerIs, whoWon, playAgain, closeWindow);
        view.setAlignment(Pos.CENTER);

        StackPane window = new StackPane();
        window.getChildren().add(view);
        window.setBackground(background);
        return new Scene(window, 400, 400);
    }

    public boolean isNewFirstWindow() {
        return newFirstWindow;
    }
}

