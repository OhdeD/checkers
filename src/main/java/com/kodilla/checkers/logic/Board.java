package com.kodilla.checkers.logic;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardRow> rows = new ArrayList<>();

    public Board() {
        for(int n=0; n<8; n++){
            rows.add(new BoardRow());
        }
    }

    public void setFigure(int col, int row, Figure figure){

    }

    public Figure getFigure (int col, int row){
        return null;
    }
}
