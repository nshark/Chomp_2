package com.company;

import java.awt.*;
import java.util.Arrays;

public class ChompPlayer {
    public Point AImove(int[] boardState){

        return null;
    }
    private int[] resolveMove(int[] boardState, Point p){
        int[] newBoardState = new int[10];
        for (int i = 0; i < 10; i++) {
            newBoardState[i] = boardState[i];
        }
        return newBoardState;
    }
}
