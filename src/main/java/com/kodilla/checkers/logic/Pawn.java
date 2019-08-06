package com.kodilla.checkers.logic;

import javafx.scene.image.Image;

public class Pawn implements Figure {
    private String colour;
    public Pawn(String colour){
        this.colour = colour;
    }

    @Override
    public String getColour() {
        return colour;
    }



}
