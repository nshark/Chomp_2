package com.company;

import java.awt.*;

public class Chip {
    Color G = new Color(100,200,100);
    Color p = new Color(200,200,50);
    public int x;
    public int alpha = 255;
    private long Lastcall = System.currentTimeMillis();
    public int y;
    public int isAlive;
    public boolean poison;
    Chip(int x, int y, boolean poison){
        this.x = x;
        this.y = y;
        isAlive = 1;
        this.poison = poison;
    }
    public boolean getIsAliveAsBool(){
        return (isAlive == 1);
    }
    public void drawSelf(Graphics g){
        long timeElapsed = System.currentTimeMillis() - Lastcall;
        Lastcall = System.currentTimeMillis();
        if (alpha >= 1) {
            g.setColor(G);
            if (poison) {
                g.setColor(p);
            }
            g.fillOval(x + 5, y + 5, 40, 40);
            g.setColor(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.black.getBlue(), alpha));
            g.drawOval(x + 5, y + 5, 40, 40);
        }
        if (!getIsAliveAsBool() && alpha >= 1){
            alpha -= Math.abs(timeElapsed);
            if (alpha < 0){
                alpha = 0;
            }
        }
        if (getIsAliveAsBool() && alpha <= 254){
            alpha += Math.abs(timeElapsed);
            if (alpha > 255){
                alpha = 255;
            }
        }
        G = new Color(G.getRed(), G.getGreen(), G.getBlue(), alpha);
        p = new Color(p.getRed(), p.getGreen(), p.getBlue(), alpha);
    }
}
