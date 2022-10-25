package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChompBoard extends Canvas implements MouseListener {
    Chip[][] board = new Chip[10][10];
    ChompBoard(){
        super();
        addMouseListener(this);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Chip(50*i, 50*j, (j == 9 && i == 0));
            }
        }
    }
    public void graphicsTick(){
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        g.clearRect(0,0,500,500);
       for (int i = 0; i < 500; i+=50) {
            g.fillRect(i-2,0,4,500);
            g.fillRect(0,i-2,500,4);
        }
        for (Chip[] C : board){
            for (Chip c : C){
                c.drawSelf(g);
            }
        }
        this.getBufferStrategy().show();
        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (int) (((float) e.getX())/50f);
        while(x <= 9){
            int y = (int) (((float) e.getY())/50f);
            while(y >= 0){
                board[x][y].isAlive = 0;
                y--;
            }
            x++;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
