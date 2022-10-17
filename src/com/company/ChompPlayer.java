package com.company;

import java.awt.*;
import java.util.Arrays;

public class ChompPlayer {
    public Point AImove(int[] boardState){
        //if the board is not a L, make it a L
        if(boardState[1] >=2){
            return new Point(1,1);
        }
        // if only poison remains, eat poison
        if (boardState[0] == 1 && boardState[1] == 0){
            return new Point(0,0);
        }
        if (boardState[0] == 1){
            return new Point(1,0);
        }
        if (boardState[1] == 1) {
            int xState = 9;
            for (int i = 0; i < 10; i++) {
                if (boardState[i] == 0){
                    xState = i;
                    break;
                }
            }
            if (boardState[boardState[0]-1] != 1){
                return new Point(0, xState);
            }
            if (boardState[0] < xState){
                return new Point(boardState[0], 0);
            }
            return new Point(1,0);
        }
        if (boardState[1] == 0){
            return new Point(0,1);
        }
        return null;
    }
    private int[] resolveMove(int[] boardState, Point p){
        int[] newBoardState = new int[10];
        System.arraycopy(boardState, 0, newBoardState, 0, 10);
        boolean cut = false;
        for (int i = 0; i < 10; i++) {
            if (p.x == i){
                cut = true;
            }
            if (cut && newBoardState[i] > p.y) {
                newBoardState[i] = p.y;
            }
        }
        return newBoardState;
    }
}
