package com.kodilla.checkers.logic;

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
