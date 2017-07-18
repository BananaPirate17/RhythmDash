package com.game.main;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by tluo on 7/12/2017.
 */
public class KeyInput extends KeyAdapter {

    public static String spaceResult = "";
    public static String fResult = "";
    public static String gResult = "";
    public static String hResult = "";
    public static String jResult = "";

    private Handler handler;
    public static ArrayList hitLanes = new ArrayList(10);
    public static ArrayList hitSetTimes = new ArrayList(10);


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        String result;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            Double nextNoteTime = (Double) Tap.spaceNotes.get(0);
            if (hit(nextNoteTime, key) != "miss") {
                hitSetTimes.add(Tap.spaceNotes.get(0));
                Tap.spaceNotes.remove(0);
                hitLanes.add(Lane.LEFTLEFT);
            }
        } else if (key == KeyEvent.VK_F) {
            Double nextNoteTime = (Double) Tap.fNotes.get(0);
            if (hit(nextNoteTime, key) != "miss") {
                hitSetTimes.add(Tap.fNotes.get(0));
                Tap.fNotes.remove(0);
                hitLanes.add(Lane.LEFT);
                System.out.println("1: " + Conductor.songPosition);
            }
        } else if (key == KeyEvent.VK_G) {
            Double nextNoteTime = (Double) Tap.gNotes.get(0);
            if (hit(nextNoteTime, key) != "miss") {
                hitSetTimes.add(Tap.gNotes.get(0));
                Tap.gNotes.remove(0);
                hitLanes.add(Lane.LEFTCENTER);
                System.out.println("2: " + Conductor.songPosition);
            }
        } else if (key == KeyEvent.VK_H) {
            Double nextNoteTime = (Double) Tap.hNotes.get(0);
            if (hit(nextNoteTime, key) != "miss") {
                hitSetTimes.add(Tap.hNotes.get(0));
                Tap.hNotes.remove(0);
                hitLanes.add(Lane.RIGHTCENTER);
                System.out.println("3: " + Conductor.songPosition);
            }
        } else if (key == KeyEvent.VK_J) {
            Double nextNoteTime = (Double) Tap.jNotes.get(0);
            if (hit(nextNoteTime, key) != "miss") {

                hitSetTimes.add(Tap.jNotes.get(0));
                Tap.jNotes.remove(0);
                hitLanes.add(Lane.RIGHT);
                System.out.println("4: " + Conductor.songPosition);
            }
        }
        else if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

    }

    public String hit(double nextNoteTime, int key) {
        double distance = Math.abs(nextNoteTime - Conductor.songPosition);
        String tempResult;
        if (distance < 0.175) {
            tempResult = "bad";
            if (distance < 0.0825) {
                tempResult = "good";
                if (distance < 0.05)
                    tempResult = "perfect!";
            }
            display(tempResult, key);
        } else
            tempResult = "miss";
        return tempResult;
    }

    public void display(String result, int key) {
        if (key == KeyEvent.VK_SPACE) {
            spaceResult = result;
        } else if (key == KeyEvent.VK_F) {
            fResult = result;
        } else if (key == KeyEvent.VK_G) {
            gResult = result;
        } else if (key == KeyEvent.VK_H) {
            hResult = result;
        } else if (key == KeyEvent.VK_J) {
            jResult = result;
        }

    }
}
