package com.game.main;

import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by tluo on 7/7/2017.
 */
public class Game extends Canvas implements Runnable {

    final JFXPanel fxPanel = new JFXPanel(); // needed to initialize javafx

    private int FPS;
    public static final int WIDTH = 800, HEIGHT = WIDTH / 4 * 3;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Conductor conductor;
    private HUD hud;

    public enum STATE {
        MENU,
        GAME
    }

    public static STATE gameState = STATE.GAME;


    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        conductor = new Conductor(handler);

        new Window(WIDTH, HEIGHT, "Rhythm Dash", this);

        hud = new HUD();

        start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop() {
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

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.DARK_GRAY);

        g.drawLine(0, 500, 800, 500); // lines
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(600, 0, 600, 600);

        g.drawString("'space'", 135, 520); // keys
        g.drawString("'F'", 245, 520);
        g.drawString("'G'", 345, 520);
        g.drawString("'H'", 445, 520);
        g.drawString("'J'", 545, 520);

        g.setColor(Color.white);
        Font bigWhite = new Font("SansSerif", Font.BOLD, 18);
        g.setFont(bigWhite);

        g.drawString(KeyInput.spaceResult, 135, 540); // results
        g.drawString(KeyInput.fResult, 225, 550);
        g.drawString(KeyInput.gResult, 325, 550);
        g.drawString(KeyInput.hResult, 425, 550);
        g.drawString(KeyInput.jResult, 525, 550);
        g.drawString("FPS: " + FPS, 700, 400);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }


    public static void main(String[] args) {
        new Game();

    }
}
