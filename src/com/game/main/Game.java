package com.game.main;

import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by tluo on 7/7/2017.
 */

public class Game extends Canvas implements Runnable {

    final JFXPanel fxPanel = new JFXPanel(); // needed to initialize javafx, don't delete

    private int FPS; //displayed FPS
    private static final int WIDTH = 800, HEIGHT = WIDTH / 4 * 3;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Conductor conductor;
    private HUD hud;

    public enum STATE { //for the menu and selection screen. First priority after first song finished
        MENU,
        GAME
    }

    public static STATE gameState = STATE.GAME;


    private Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        conductor = new Conductor(handler);

        new Window(WIDTH, HEIGHT, "Rhythm Dash", this);

        hud = new HUD();

        start();
    }

    private synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                FPS = frames;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        conductor.tick();
        hud.tick();

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        KeyInput keys = new KeyInput(handler);

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.DARK_GRAY);

        g.drawLine(0, 500, 800, 500);// lines
        g.drawLine(0, 520, 800, 520);

        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(600, 0, 600, 600);

        g.drawString("'SPACE'", 135, 515); // keys
        g.drawString("'F'", 245, 515);
        g.drawString("'G'", 345, 515);
        g.drawString("'H'", 445, 515);
        g.drawString("'J'", 545, 515);

        g.setColor(Color.white);
        Font bigWhite = new Font("SansSerif", Font.BOLD, 18);
        g.setFont(bigWhite);

        g.drawString(KeyInput.spaceResult, 135, 550); // results
        g.drawString(KeyInput.fResult, 225, 550);
        g.drawString(KeyInput.gResult, 325, 550);
        g.drawString(KeyInput.hResult, 425, 550);
        g.drawString(KeyInput.jResult, 525, 550);
        g.drawString("FPS: " + FPS, 660, 495);
        g.drawString("Score: " + keys.getScore(), 660, 35);
        g.drawString("Combo: x" + KeyInput.combo, 660, 60);

        g.setColor(Color.red);
        g.drawString("Fire Aura", 650, 250);
        g.drawString("Kid2Will", 675, 275);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }


    public static void main(String[] args) {
        new Game();

    }
}
