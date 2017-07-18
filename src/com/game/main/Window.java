package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by tluo on 7/7/2017.
 */
public class Window extends JFrame {
    public Window(int width, int height, String title, Game game) { //constructor for a window
        JFrame frame = new JFrame(title);

        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);

        //Game.start();        moved to Game class

        frame.setVisible(true);
        frame.requestFocus();

        /*Font titleFont = new Font("SansSerif", Font.BOLD, 40);

        JButton jbtStart = new JButton("Start");
        JButton jbtQuit = new JButton("Quit");
        JLabel jlblTitle = new JLabel("Rhythm Dash", SwingConstants.CENTER);
        jlblTitle.setFont(titleFont);
        JPanel menuPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JPanel titlePanel = new JPanel(new GridLayout(1, 1, 0, 0));
        menuPanel.setMaximumSize(new Dimension(200, 400));
        menuPanel.add(jbtStart);
        menuPanel.add(jbtQuit);
        titlePanel.add(jlblTitle);

        menuPanel.setVisible(true);
        titlePanel.setVisible(true);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(titlePanel);
        box.add(Box.createVerticalGlue());
        box.add(menuPanel);
        box.add(Box.createVerticalGlue());
        frame.add(box);

        frame.setVisible(true);

        jbtQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jbtStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Handler handler;
                menuPanel.setVisible(false);
                titlePanel.setVisible(false);
                box.setVisible(false);
                Game.gameState = Game.STATE.GAME;

            }
        });
        */



    }
}







