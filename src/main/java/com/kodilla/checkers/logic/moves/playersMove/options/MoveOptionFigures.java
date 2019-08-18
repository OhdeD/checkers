package com.kodilla.checkers.logic.moves.playersMove.options;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class MoveOptionFigures {
   private List<Circle> highlight = new ArrayList<>();

    public MoveOptionFigures() {
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
    }

    public Circle show(int i) {
        return highlight.get(i);
    }
}
