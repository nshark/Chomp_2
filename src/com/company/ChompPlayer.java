package com.company;

import java.awt.*;
import java.util.HashMap;

public class ChompPlayer {
    HashMap<Long, Point> states = new HashMap<>();
    ChompPlayer(){
        int[] c = new int[10];
        for (int i = 0; i < 10; i++) {
            c[i] = 0;
        }
        states.put(process(c), new Point(0,0));
    }
    public Point AImove(int[] boardState){
        HashMap<Point, int[]> possibleMove =  possibleMoves(boardState);
        for(Point p : possibleMove.keySet()){
            if (!states.containsKey(process(possibleMove.get(p)))){
                states.put(process(possibleMove.get(p)), AImove(possibleMove.get(p)));
            }
            if (states.get(process(possibleMove.get(p))) == null){
                return p;
            }
        }
        return null;
    }
    private int[] resolveMove(int[] boardState, int x, int y){
        int[] newBoardState = new int[10];
        System.arraycopy(boardState, 0, newBoardState, 0, 10);
        boolean cut = false;
        for (int i = 0; i < 10; i++) {
            if (x == i){
                cut = true;
            }
            if (cut && newBoardState[i] > y) {
                newBoardState[i] = y;
            }
        }
        return newBoardState;
    }
    private HashMap<Point, int[]> possibleMoves(int[] boardState){
        HashMap<Point, int[]> list = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (boardState[i] > j){
                    list.put(new Point(i,j), resolveMove(boardState, i, j));
                }
            }
        }
        return list;
    }
    public long process(int[] state){
        long v = 0;
        for (int i = 0; i < 10; i++) {
            v += state[i] * Math.pow(11,i);
        }
        return v;
    }
}
