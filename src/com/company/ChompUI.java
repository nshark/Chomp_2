package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ChompUI extends JFrame implements Runnable, ActionListener {
    public Panel panel;
    public Panel buttonPanel;
    private boolean notQuit = true;
    public ChompBoard chompBoard;
    public Button[] Buttons = new Button[]{
            new Button("New Game"),
            new Button("New 3x3 Game"),
            new Button("New Random Game"),
            new Button("Random Move"),
            new Button("AI Move"),
            new Button("Quit")
    };
    public ChompPlayer cp = new ChompPlayer();
    ChompUI(){
        //Set up graphics
        super("Chomp");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new Panel(new BorderLayout());
        buttonPanel = new Panel(new GridLayout(2,3));
        add(panel);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        Label l = new Label("Chomp 2.0");
        l.setAlignment(Label.CENTER);
        l.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        panel.add(l, BorderLayout.NORTH);
        for (Button b : Buttons) {
            b.addActionListener(this);
            buttonPanel.add(b);
        }
        setVisible(true);
        pack();
        chompBoard = new ChompBoard();
        chompBoard.setBounds(0,0,500,500);
        panel.add(chompBoard, BorderLayout.CENTER);
        chompBoard.setVisible(true);
        chompBoard.createBufferStrategy(2);
        pack();
        requestFocus();
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(notQuit){
            chompBoard.graphicsTick();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ("New Random Game") -> {
                for (Chip[] chips : chompBoard.board) {
                    for (Chip chip : chips) {
                        chip.isAlive = 1;
                    }
                }
                int i = 10 - new Random().nextInt(9);
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k < 10; k++) {
                        if (k > i-1) {
                            chompBoard.board[j][9-k].isAlive = 0;
                        }
                        else{
                            chompBoard.board[j][9-k].isAlive = 1;
                        }
                    }
                    i -= new Random().nextInt(0,i+1);

                }
            }
            case ("Quit") -> {
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                notQuit = false;
            }
            case ("New Game") -> {
                for (Chip[] chips : chompBoard.board) {
                    for (Chip chip : chips) {
                        chip.isAlive = 1;
                    }
                }
            }
            case ("New 3x3 Game") -> {
                for (Chip[] chips : chompBoard.board) {
                    for (Chip chip : chips) {
                        if (chip.x < 101 && chip.y > 349) {
                            chip.isAlive = 1;
                        } else {
                            chip.isAlive = 0;
                        }
                    }
                }
            }
            case ("AI Move") -> {
                int[] boardState = new int[10];
                for (int i = 0; i < 10; i++) {
                    int x = 0;
                    for (int j = 0; j < 10; j++) {
                        x += chompBoard.board[i][j].isAlive;
                    }
                    boardState[i] = x;
                }
                Point point = cp.AImove(boardState);
                if (point == null || boardState[point.x] <= point.y) {
                    point = RandomChompPlayer.RandMove(boardState);
                }
                int x1 = point.x;
                while (x1 <= 9) {
                    int y1 = point.y;
                    while (y1 <= 9) {
                        chompBoard.board[x1][9 - y1].isAlive = 0;
                        y1++;
                    }
                    x1++;
                }
            }
            case ("Random Move") -> {
                int[] boardState = new int[10];
                for (int i = 0; i < 10; i++) {
                    int x = 0;
                    for (int j = 0; j < 10; j++) {
                        x += chompBoard.board[i][j].isAlive;
                    }
                    boardState[i] = x;
                }
                Point point = RandomChompPlayer.RandMove(boardState);
                int x1 = point.x;
                while (x1 <= 9) {
                    int y1 = point.y;
                    while (y1 <= 9) {
                        chompBoard.board[x1][9 - y1].isAlive = 0;
                        y1++;
                    }
                    x1++;
                }
            }
        }
    }
}
