package com.game.main;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by tluo on 7/7/2017.
 */
public class Tap extends NoteObject {

    public static ArrayList spaceNotes = new ArrayList(5);
    public static ArrayList fNotes = new ArrayList(5);
    public static ArrayList gNotes = new ArrayList(5);
    public static ArrayList hNotes = new ArrayList(5);
    public static ArrayList jNotes = new ArrayList(5);

    public Tap(Lane lane, int y, Note note, double hitTime, Handler handler) {
        super(lane, y, note);
        this.handler = handler;
        this.hitTime = hitTime;
        switch (lane) {
            case LEFTLEFT: spaceNotes.add(this.getHitTime());
                break;
            case LEFT: fNotes.add(this.getHitTime());
                break;
            case LEFTCENTER:gNotes.add(this.getHitTime());
                break;
            case RIGHTCENTER: hNotes.add(this.getHitTime());
                break;
            case RIGHT: jNotes.add(this.getHitTime());
                break;
            default: spaceNotes.add(this.getHitTime());
        }
    }

    public void tick() {

        this.y = (int) Math.round((Conductor.songPosition + 1.0 - hitTime) / 1 * 500);
        if (this.y > 543.75) {
            switch (lane) {
                case LEFTLEFT:
                    spaceNotes.remove(this.getHitTime());
                    KeyInput.spaceResult = "miss";
                    break;
                case LEFT:
                    fNotes.remove(this.getHitTime());
                    KeyInput.fResult = "miss";
                    break;
                case LEFTCENTER:
                    gNotes.remove(this.getHitTime());
                    KeyInput.gResult = "miss";
                    break;
                case RIGHTCENTER:
                    hNotes.remove(this.getHitTime());
                    KeyInput.hResult = "miss";
                    break;
                case RIGHT:
                    jNotes.remove(this.getHitTime());
                    KeyInput.jResult = "miss";
                    break;
            }
            handler.removeNote(this);
        }
        if (!KeyInput.hitLanes.isEmpty()) //removing notes after hit
            for (int i = 0; i < KeyInput.hitSetTimes.size(); i++) {
                if (KeyInput.hitLanes.get(i) == this.lane && KeyInput.hitSetTimes.get(i).equals(this.hitTime)) {
                    handler.removeNote(this);
                    KeyInput.hitLanes.remove(i);
                    KeyInput.hitSetTimes.remove(i);
                }
            }
    }


    public void render(Graphics g) {

        int x;
        switch (lane) {
            case LEFTLEFT: x = 105;
                break;
            case LEFT: x = 205;
                break;
            case LEFTCENTER: x = 305;
                break;
            case RIGHTCENTER: x = 405;
                break;
            case RIGHT: x = 505;
                break;
            default: x = 0;
        }
        g.setColor(Color.green);
        if (lane == Lane.LEFTLEFT)
            g.setColor(Color.orange);
        g.fillRect(x, y, 90, 20);

    }
}
