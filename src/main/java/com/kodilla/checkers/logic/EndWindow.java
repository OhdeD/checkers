package com.kodilla.checkers.logic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class EndWindow {
    private Image boardPart = new Image("file:src/main/resources/windowBackground.png");
    private BackgroundSize size = new BackgroundSize(400, 400, false, false, true, true);
    private BackgroundImage windowBackground = new BackgroundImage(boardPart, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
    private Background background = new Background(windowBackground);
    private String winner;

    public EndWindow(String winner) {
        this.winner = winner;
    }

    public Scene openEndWindow() {

        Label endText = new Label("End of the Game");
        endText.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 25));
        endText.setPadding(new Insets(30, 0, 0, 0));
        endText.setTextFill(Color.WHITE);

        Label winnerIs = new Label("The winner is:" );
        winnerIs.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 15));
        winnerIs.setPadding(new Insets(10, 0, 0, 0));
        winnerIs.setTextFill(Color.WHITE);

        Label whoWon = new Label("" + winner );
        whoWon.setFont(Font.font("Vineta BT", FontWeight.BOLD, FontPosture.REGULAR, 10));
        whoWon.setPadding(new Insets(10, 0, 0, 0));
        whoWon.setTextFill(Color.WHITE);


        VBox view = new VBox(0);
        view.getChildren().addAll(endText, winnerIs, whoWon);
        view.setAlignment(Pos.CENTER);

        StackPane window = new StackPane();
        window.getChildren().add(view);
        window.setBackground(background);
        return new Scene(window, 400, 400);
    }

}

