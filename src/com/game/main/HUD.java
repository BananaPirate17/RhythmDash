package com.game.main;

import java.awt.*;

public class HUD {

    private static double duration;
    private static double time;


   public void tick() {
       time = Conductor.songPosition;
       duration = Conductor.songDuration;

    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, 800, 5);
        g.setColor(Color.blue);
        g.fillRect(0, 0, (int)(time / duration * 800), 5);

    }
}
