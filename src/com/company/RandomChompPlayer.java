package com.company;

import java.awt.*;
import java.util.Random;

public class RandomChompPlayer {
    public static Point RandMove(int[] boardState){
        Random random = new Random();
        if (boardState[0] == 1 && boardState[1] == 0){
            return new Point(0,0);
        }
        int xstate = 0;
        for (int i = 0; i < 10; i++) {
            if (boardState[i] != 0){
                xstate++;
            }
        }
        if (boardState[0] == 0){
            return new Point(random.nextInt(0,xstate), 0);
        }
        if (boardState[1] == 0){
            return new Point(0, random.nextInt(0, boardState[0]));
        }
        int x = random.nextInt(0, xstate);
        int y = random.nextInt(0,boardState[x]);
        if(y == 0 && x == 0){
            y = random.nextInt(1, boardState[x]);
        }
        return new Point(x, y);
    }
}
